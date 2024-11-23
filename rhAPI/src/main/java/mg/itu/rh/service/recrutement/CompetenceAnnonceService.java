package mg.itu.rh.service.recrutement;

import jakarta.transaction.Transactional;
import mg.itu.rh.dto.recrutement.CompetenceAnnonceDTO;
import mg.itu.rh.entity.recrutement.Annonce;
import mg.itu.rh.entity.recrutement.CompetenceAnnonce;
import mg.itu.rh.entity.id.IdCompetenceAnnonce;
import mg.itu.rh.repository.recrutement.CompetenceAnnonceRepository;
import mg.itu.rh.service.critere.CompetenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetenceAnnonceService {
    private final CompetenceService competenceService;
    private final CompetenceAnnonceRepository competenceAnnonceRepository;

    public CompetenceAnnonceService( CompetenceService competenceService, CompetenceAnnonceRepository competenceAnnonceRepository ) {
        this.competenceService = competenceService;
        this.competenceAnnonceRepository = competenceAnnonceRepository;
    }

    @Transactional
    public void saveAllFromDTO(List<CompetenceAnnonceDTO> competences, Annonce annonce ) {
        for ( CompetenceAnnonceDTO competence : competences )
            annonce.addCompetence(save( competence, annonce ));
    }

    @Transactional
    public CompetenceAnnonce save( CompetenceAnnonceDTO dto, Annonce annonce ) {
        CompetenceAnnonce ca = dto.getCompetenceAnnonce( competenceService );
        IdCompetenceAnnonce id = new IdCompetenceAnnonce( dto.getCompetence().getIdCompetence(), annonce.getIdAnnonce() );
        ca.setId( id );
        ca.setAnnonce( annonce );
        ca.setCompetence(competenceService.findById(dto.getCompetence().getIdCompetence()));
        return competenceAnnonceRepository.save( ca );
    }

    public List<CompetenceAnnonce> findAllByIdAnnonce( Long idAnnonce ) {
        return competenceAnnonceRepository.findAllByIdAnnonce( idAnnonce );
    }
}
