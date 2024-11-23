package mg.itu.rh.service.recrutement;

import jakarta.transaction.Transactional;
import mg.itu.rh.dto.poste.CompetencePosteDTO;
import mg.itu.rh.entity.Poste;
import mg.itu.rh.entity.id.IdCompetencePoste;
import mg.itu.rh.entity.recrutement.CompetencePoste;
import mg.itu.rh.repository.recrutement.CompetencePosteRepository;
import mg.itu.rh.service.critere.CompetenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetencePosteService {
    private final CompetenceService competenceService;
    private final CompetencePosteRepository competenceAnnonceRepository;

    public CompetencePosteService(CompetenceService competenceService, CompetencePosteRepository competenceAnnonceRepository ) {
        this.competenceService = competenceService;
        this.competenceAnnonceRepository = competenceAnnonceRepository;
    }

    @Transactional
    public void saveAllFromDTO(List<CompetencePosteDTO> competences, Poste poste ) {
        for ( CompetencePosteDTO competence : competences )
            poste.addCompetence(save( competence, poste ));
    }

    @Transactional
    public CompetencePoste save(CompetencePosteDTO dto, Poste poste ) {
        CompetencePoste ca = dto.getCompetenceAnnonce( competenceService );
        IdCompetencePoste id = new IdCompetencePoste( dto.getCompetence().getIdCompetence(), poste.getIdPoste() );
        ca.setId( id );
        ca.setPoste( poste );
        ca.setCompetence(competenceService.findById(dto.getCompetence().getIdCompetence()));
        return competenceAnnonceRepository.save( ca );
    }

    public List<CompetencePoste> findAllByIdAnnonce(Long idPoste ) {
        return competenceAnnonceRepository.findAllByIdPoste( idPoste );
    }
}
