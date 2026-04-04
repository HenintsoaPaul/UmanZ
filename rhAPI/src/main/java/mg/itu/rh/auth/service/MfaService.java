package mg.itu.rh.auth.service;

import mg.itu.rh.auth.dto.MFARequest;
import mg.itu.rh.auth.exception.InvalidCredentialsException;
import mg.itu.rh.auth.exception.InvalidMfaException;
import mg.itu.rh.entity.talent.Talent;
import mg.itu.rh.repository.talent.TalentRepository;
import mg.itu.rh.service.interne.EmailService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.mail.MessagingException;

import java.util.List;
import java.util.Map;

@Service
public class MfaService {
    private final TalentRepository talentRepository;
    private final TwoFactorService twoFactorService;
    private final EmailService emailService;

    public MfaService(TalentRepository talentRepository,
            TwoFactorService twoFactorService, EmailService emailService) {
        this.talentRepository = talentRepository;
        this.twoFactorService = twoFactorService;
        this.emailService = emailService;
    }

    public Map<String, String> setupMfa(Map<String, String> request) {
        String email = request.get("email");

        Talent talent = talentRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException());

        // Generate and send email code for setup verification
        String emailCode = twoFactorService.generateEmailCode();
        talent.setMfaEmailCode(twoFactorService.encryptScratchCodes(List.of(emailCode)));

        try {
            emailService.sendMfaCode(email, emailCode);
        } catch (MessagingException e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage());
        }
        talentRepository.save(talent);

        return Map.of("message", "Code envoyé par email");
    }

    public Map<String, List<String>> confirmMfa(MFARequest mfaRequest) {
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
            throw new InvalidMfaException();
        }
    }

    public boolean verifyMfa(Talent talent, MFARequest mfaRequest) {
        boolean verified = false;

        if (mfaRequest.getScratchCode() != null) { // On mfa setup (already logged)
            boolean isScratchCodeValid = twoFactorService.verifyScratchCode(talent.getMail(),
                    talent.getMfaScratchCodes(), mfaRequest.getScratchCode());

            if (isScratchCodeValid) {
                List<String> updatedCodes = twoFactorService.removeUsedScratchCode(talent.getMfaScratchCodes(),
                        mfaRequest.getScratchCode());

                talent.setMfaScratchCodes(twoFactorService.encryptScratchCodes(updatedCodes));
                talentRepository.save(talent);

                verified = true;
            }
        } else if (twoFactorService.verifyEmailCode(talent.getMail(), talent.getMfaEmailCode(),
                mfaRequest.getCode() + "")) { // On mfa verify (on login)
            verified = true;
            talent.setMfaEmailCode(null); // Clear code after use
            talentRepository.save(talent);
        }

        return verified;
    }
}
