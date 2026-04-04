package mg.itu.rh.auth;

import mg.itu.rh.auth.dto.LoginRequest;
import mg.itu.rh.auth.dto.LoginResponse;
import mg.itu.rh.auth.dto.MFARequest;
import mg.itu.rh.auth.exception.*;
import mg.itu.rh.auth.service.AuthService;
import mg.itu.rh.auth.service.MfaService;
import mg.itu.rh.auth.service.TwoFactorService;
import jakarta.validation.Valid;
import mg.itu.rh.entity.talent.Talent;
import mg.itu.rh.repository.talent.TalentRepository;
import mg.itu.rh.service.interne.EmailService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final MfaService mfaService;
    private final TalentRepository talentRepository;

    public AuthController(AuthService authService, MfaService mfaService, TwoFactorService twoFactorService,
            TalentRepository talentRepository, EmailService emailService) {
        this.authService = authService;
        this.mfaService = mfaService;
        this.talentRepository = talentRepository;
    }

    @PostMapping
    public LoginResponse authenticate(@RequestBody @Valid LoginRequest authDTO) {
        return authService.authenticate(authDTO);
    }

    @PostMapping("/mfa/setup")
    public Map<String, String> setupMfa(@RequestBody Map<String, String> request) {
        return mfaService.setupMfa(request);
    }

    @PostMapping("/mfa/confirm")
    public Map<String, List<String>> confirmMfa(@RequestBody @Valid MFARequest mfaRequest) {
        return mfaService.confirmMfa(mfaRequest);
    }

    @PostMapping("/mfa/verify")
    public LoginResponse verifyMfa(@RequestBody @Valid MFARequest mfaRequest) {
        Talent talent = talentRepository.findByEmail(mfaRequest.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        return this.verifyMfa(talent, mfaRequest);
    }

    private LoginResponse verifyMfa(Talent talent, MFARequest mfaRequest) {
        boolean verified = this.mfaService.verifyMfa(talent, mfaRequest);

        if (verified) {
            try {
                return authService.findByEmailAndPasswordBypassingMfa(talent);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Error during final authentication");
            }
        } else {
            throw new InvalidMfaException();
        }
    }
}