package mg.itu.rh.auth;

import mg.itu.rh.auth.exception.TalentNotFoundException;
import mg.itu.rh.dto.talent.AuthDTO;
import mg.itu.rh.dto.talent.AuthResponseDTO;
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

    public AuthResponseDTO findByEmailAndPassword(AuthDTO authDTO) throws TalentNotFoundException {
        String email = authDTO.getEmail(), pwd = authDTO.getPassword();
        Talent t = talentRepository.findByEmailAndPassword(email, pwd)
                .orElseThrow(() -> new TalentNotFoundException());

        AuthResponseDTO responseDTO = new AuthResponseDTO(t);
        Optional<Contrat> c = contratService.findActualContratByIdTalent(t.getIdTalent());
        c.ifPresent(contrat -> responseDTO.setIdContrat(contrat.getIdContrat()));

        return responseDTO;
    }
}
