package mg.itu.rh.service;

import mg.itu.rh.dto.ContratDTO;
import mg.itu.rh.entity.Contrat;
import mg.itu.rh.repository.ContratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratService {

    @Autowired
    private ContratRepository contratRepository;

    @Autowired
    private TalentService talentService;

    @Autowired
    private TypeContratService typeContratService;

    @Autowired
    private PosteService posteService;

    public Contrat findActualContratByIdTalent(Long idTalent){
        return contratRepository.findActualContratByIdTalent(idTalent).orElseThrow(()->new RuntimeException("Cette personne n'est pas un employe ou n'est plus un employe"));
    }

    public Contrat findById(Long idContrat){
        return contratRepository.findById(idContrat).orElseThrow(()->new RuntimeException("Contrat non reconnue"));
    }

    public Contrat save(Contrat contrat){
        return contratRepository.save(contrat);
    }

    public Contrat save(ContratDTO contratDTO){
        Contrat contrat=new Contrat(contratDTO);
        System.out.println(contratDTO.getIdTalent()+": idTalent");
        contrat.setTalent(talentService.findById(contratDTO.getIdTalent()));
        contrat.setTypeContrat(typeContratService.findById(contratDTO.getIdTypeContrat()));
        contrat.setPoste(posteService.findById(contratDTO.getIdPoste()));
        return this.save(contrat);
    }

    public List<Contrat> findAll() {
        return contratRepository.findAll();
    }
}
