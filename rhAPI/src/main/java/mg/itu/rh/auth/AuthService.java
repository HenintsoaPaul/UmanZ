package mg.itu.rh.auth;

import mg.itu.rh.auth.exception.AccountNotFoundException;
import mg.itu.rh.entity.interne.Contrat;
import mg.itu.rh.entity.talent.Talent;
import mg.itu.rh.repository.talent.TalentRepository;
import mg.itu.rh.service.interne.ContratService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final TalentRepository talentRepository;
    private final ContratService contratService;

    public AuthService(TalentRepository talentRepository, ContratService contratService) {
        this.talentRepository = talentRepository;
        this.contratService = contratService;
    }

    public LoginResponse findByEmailAndPassword(LoginRequest authDTO) throws AccountNotFoundException {
        String email = authDTO.getEmail(), pwd = authDTO.getPassword();
        Talent t = talentRepository.findByEmailAndPassword(email, pwd)
                .orElseThrow(() -> new AccountNotFoundException());

        LoginResponse responseDTO = new LoginResponse(t);
        Optional<Contrat> c = contratService.findActualContratByIdTalent(t.getIdTalent());
        c.ifPresent(contrat -> responseDTO.setIdContrat(contrat.getIdContrat()));

        return responseDTO;
    }
}
