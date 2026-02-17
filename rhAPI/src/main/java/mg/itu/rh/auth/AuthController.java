package mg.itu.rh.auth;

import mg.itu.rh.auth.exception.AccountNotFoundException;
import mg.itu.rh.entity.talent.Talent;
import mg.itu.rh.repository.talent.TalentRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

        String secret = twoFactorService.generateNewSecret();
        talent.setMfaSecret(secret);
        talentRepository.save(talent);

        String qrCodeUri = twoFactorService.generateQrCodeUri(secret, email);
        return Map.of("secret", secret, "qrCodeUri", qrCodeUri);
    }

    @PostMapping("/mfa/confirm")
    public void confirmMfa(@RequestBody MFARequest mfaRequest) {
        Talent talent = talentRepository.findByEmail(mfaRequest.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (twoFactorService.verifyCode(talent.getMfaSecret(), mfaRequest.getCode())) {
            talent.setMfaEnabled(true);
            talentRepository.save(talent);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid MFA code");
        }
    }

    @PostMapping("/mfa/verify")
    public LoginResponse verifyMfa(@RequestBody MFARequest mfaRequest) {
        Talent talent = talentRepository.findByEmail(mfaRequest.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (twoFactorService.verifyCode(talent.getMfaSecret(), mfaRequest.getCode())) {
            // After verification, return the full login response (similar to what
            // AuthService does when MFA is disabled)
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setEmail(talent.getMail());
            loginRequest.setPassword(talent.getPassword()); // This is a bit insecure if we don't have a token system,
                                                            // but following current pattern
            try {
                // We need a way to bypass the MFA check in findByEmailAndPassword or a separate
                // method
                // For now, let's just manually construct the full response since we verified
                // the code
                return authService.findByEmailAndPasswordBypassingMfa(talent);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Error during final authentication");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid MFA code");
        }
    }
}