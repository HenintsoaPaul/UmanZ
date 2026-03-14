package mg.itu.rh.auth;

import mg.itu.rh.auth.exception.AccountNotFoundException;
import mg.itu.rh.entity.talent.Talent;
import mg.itu.rh.repository.talent.TalentRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final TwoFactorService twoFactorService;
    private final TalentRepository talentRepository;

    public AuthController(AuthService authService, TwoFactorService twoFactorService,
            TalentRepository talentRepository) {
        this.authService = authService;
        this.twoFactorService = twoFactorService;
        this.talentRepository = talentRepository;
    }

    @PostMapping
    @Transactional
    public LoginResponse authenticate(@RequestBody LoginRequest authDTO) {
        try {
            LoginResponse response = authService.findByEmailAndPassword(authDTO);
            if (response.isMfaRequired()) {
                Talent talent = talentRepository.findByEmail(authDTO.getEmail())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

                String emailCode = twoFactorService.generateEmailCode();
                talent.setMfaEmailCode(twoFactorService.encryptScratchCodes(List.of(emailCode)));
                talentRepository.save(talent);

                twoFactorService.sendMfaCodeViaEmail(talent.getMail(), emailCode);
            }
            return response;
        } catch (AccountNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/mfa/setup")
    public Map<String, String> setupMfa(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        Talent talent = talentRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // Keep TOTP secret generation for future use
        String encryptedSecret = twoFactorService.generateNewSecret();
        talent.setMfaSecret(encryptedSecret);

        // Generate and send email code for setup verification
        String emailCode = twoFactorService.generateEmailCode();
        talent.setMfaEmailCode(twoFactorService.encryptScratchCodes(List.of(emailCode)));

        twoFactorService.sendMfaCodeViaEmail(email, emailCode);
        talentRepository.save(talent);

        return Map.of("message", "Code envoyé par email");
    }

    @PostMapping("/mfa/confirm")
    public Map<String, List<String>> confirmMfa(@RequestBody MFARequest mfaRequest) {
        Talent talent = talentRepository.findByEmail(mfaRequest.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (twoFactorService.verifyEmailCode(talent.getMail(), talent.getMfaEmailCode(), mfaRequest.getCode() + "")) {
            talent.setMfaEnabled(true);
            talent.setMfaEmailCode(null); // Clear setup code
            List<String> scratchCodes = twoFactorService.generateScratchCodes();
            talent.setMfaScratchCodes(twoFactorService.encryptScratchCodes(scratchCodes));
            talentRepository.save(talent);
            return Map.of("scratchCodes", scratchCodes);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid MFA code");
        }
    }

    @PostMapping("/mfa/verify")
    public LoginResponse verifyMfa(@RequestBody MFARequest mfaRequest) {
        Talent talent = talentRepository.findByEmail(mfaRequest.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        boolean verified = false;
        if (mfaRequest.getScratchCode() != null) {
            if (twoFactorService.verifyScratchCode(talent.getMail(), talent.getMfaScratchCodes(),
                    mfaRequest.getScratchCode())) {
                List<String> updatedCodes = twoFactorService.removeUsedScratchCode(talent.getMfaScratchCodes(),
                        mfaRequest.getScratchCode());
                talent.setMfaScratchCodes(twoFactorService.encryptScratchCodes(updatedCodes));
                talentRepository.save(talent);
                verified = true;
            }
        } else if (twoFactorService.verifyEmailCode(talent.getMail(), talent.getMfaEmailCode(),
                mfaRequest.getCode() + "")) {
            verified = true;
            talent.setMfaEmailCode(null); // Clear code after use
            talentRepository.save(talent);
        } else if (twoFactorService.verifyCode(talent.getMail(), talent.getMfaSecret(), mfaRequest.getCode())) {
            verified = true;
        }

        if (verified) {
            try {
                return authService.findByEmailAndPasswordBypassingMfa(talent);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Error during final authentication");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid MFA code or scratch code");
        }
    }
}