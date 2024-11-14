package mg.itu.rh.service;

import mg.itu.rh.dto.ContratDTO;
import mg.itu.rh.entity.Contrat;
import mg.itu.rh.entity.TypeContrat;
import mg.itu.rh.repository.ContratRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratService {
    private final ContratRepository contratRepository;
    private final TalentService talentService;
    private final TypeContratService typeContratService;
    private final PosteService posteService;

    public ContratService( ContratRepository contratRepository, TalentService talentService, TypeContratService typeContratService, PosteService posteService ) {
        this.contratRepository = contratRepository;
        this.talentService = talentService;
        this.typeContratService = typeContratService;
        this.posteService = posteService;
    }

    public Contrat findActualContratByIdTalent( Long idTalent ) {
        return contratRepository.findActualContratByIdTalent( idTalent ).orElseThrow( () -> new RuntimeException( "Cette personne n'est pas un employe ou n'est plus un employe" ) );
    }

    public Contrat findById( Long idContrat ) {
        return contratRepository.findById( idContrat ).orElseThrow( () -> new RuntimeException( "Contrat non reconnue" ) );
    }

    public Contrat save( Contrat contrat ) {
        return contratRepository.save( contrat );
    }

    public Contrat save( ContratDTO contratDTO ) {
        Contrat contrat = new Contrat( contratDTO );
        System.out.println("idTalent: " + contratDTO.getIdTalent() );
        System.out.println("idPoste: " + contratDTO.getIdPoste() );

        contrat.setTalent( talentService.findById( contratDTO.getIdTalent() ) );
        contrat.setPoste( posteService.findById( contratDTO.getIdPoste() ) );
        TypeContrat tc = typeContratService.findById( contratDTO.getIdTypeContrat() );
        contrat.setTypeContrat( tc );
        contrat.setContrat( tc.getTypeContrat() );
        return this.save( contrat );
    }

    public List<Contrat> findAll() {
        return contratRepository.findAll();
    }
}
