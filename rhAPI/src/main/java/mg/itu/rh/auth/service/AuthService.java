package mg.itu.rh.auth.service;

import mg.itu.rh.auth.dto.LoginRequest;
import mg.itu.rh.auth.dto.LoginResponse;
import mg.itu.rh.auth.exception.InvalidCredentialsException;
import mg.itu.rh.entity.interne.Contrat;
import mg.itu.rh.entity.talent.Talent;
import mg.itu.rh.repository.talent.TalentRepository;
import mg.itu.rh.service.interne.ContratService;
import mg.itu.rh.service.interne.EmailService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.mail.MessagingException;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {
    private final TalentRepository talentRepository;
    private final ContratService contratService;
    private final TwoFactorService twoFactorService;
    private final EmailService emailService;

    public AuthService(TalentRepository talentRepository, ContratService contratService,
            TwoFactorService twoFactorService, EmailService emailService) {
        this.talentRepository = talentRepository;
        this.contratService = contratService;
        this.twoFactorService = twoFactorService;
        this.emailService = emailService;
    }

    private LoginResponse findByEmailAndPassword(LoginRequest authDTO) throws InvalidCredentialsException {
        String email = authDTO.getEmail(),
                pwd = authDTO.getPassword();

        Talent t = talentRepository.findByEmailAndPassword(email, pwd)
                .orElseThrow(() -> new InvalidCredentialsException());

        LoginResponse responseDTO = new LoginResponse(t);

        if (t.isMfaEnabled()) {
            responseDTO.setMfaRequired(true);
            return responseDTO;
        }

        Optional<Contrat> c = contratService.findActualContratByIdTalent(t.getIdTalent());

        c.ifPresent(contrat -> responseDTO.setIdContrat(contrat.getIdContrat()));

        return responseDTO;
    }

    public LoginResponse authenticate(LoginRequest authDTO) throws InvalidCredentialsException {
        try {
            LoginResponse response = this.findByEmailAndPassword(authDTO);

            if (response.isMfaRequired()) {
                Talent talent = talentRepository.findByEmail(authDTO.getEmail())
                        .orElseThrow(() -> new InvalidCredentialsException());

                String emailCode = twoFactorService.generateEmailCode();
                talent.setMfaEmailCode(twoFactorService.encryptScratchCodes(List.of(emailCode)));

                emailService.sendMfaCode(talent.getMail(), emailCode);

                talentRepository.save(talent);
            }

            return response;
        } catch (MessagingException e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage());
        }
    }

    public LoginResponse findByEmailAndPasswordBypassingMfa(Talent t) {
        LoginResponse responseDTO = new LoginResponse(t);

        Optional<Contrat> c = contratService.findActualContratByIdTalent(t.getIdTalent());

        c.ifPresent(contrat -> responseDTO.setIdContrat(contrat.getIdContrat()));

        return responseDTO;
    }
}
