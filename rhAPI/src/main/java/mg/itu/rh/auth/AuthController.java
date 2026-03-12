package mg.itu.rh.auth;

import mg.itu.rh.auth.exception.AccountNotFoundException;
import mg.itu.rh.entity.talent.Talent;
import mg.itu.rh.repository.talent.TalentRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public LoginResponse authenticate(@RequestBody LoginRequest authDTO) {
        try {
            return authService.findByEmailAndPassword(authDTO);
        } catch (AccountNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/mfa/setup")
    public Map<String, String> setupMfa(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        Talent talent = talentRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        String encryptedSecret = twoFactorService.generateNewSecret();
        talent.setMfaSecret(encryptedSecret);
        talentRepository.save(talent);

        String secret = twoFactorService.decrypt(encryptedSecret);
        String qrCodeUri = twoFactorService.generateQrCodeUri(secret, email);
        return Map.of("secret", secret, "qrCodeUri", qrCodeUri);
    }

    @PostMapping("/mfa/confirm")
    public Map<String, List<String>> confirmMfa(@RequestBody MFARequest mfaRequest) {
        Talent talent = talentRepository.findByEmail(mfaRequest.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (twoFactorService.verifyCode(talent.getMail(), talent.getMfaSecret(), mfaRequest.getCode())) {
            talent.setMfaEnabled(true);
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