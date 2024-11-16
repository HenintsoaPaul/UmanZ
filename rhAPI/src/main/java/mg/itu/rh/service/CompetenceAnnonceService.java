package mg.itu.rh.service;

import jakarta.transaction.Transactional;
import mg.itu.rh.dto.CompetenceAnnonceDTO;
import mg.itu.rh.entity.Annonce;
import mg.itu.rh.entity.CompetenceAnnonce;
import mg.itu.rh.entity.id.IdCompetenceAnnonce;
import mg.itu.rh.repository.CompetenceAnnonceRepository;
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
    public void saveAllFromDTO( List<CompetenceAnnonceDTO> competences, Annonce annonce ) {
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
        Long etatCandidature = 2L;
        return competenceAnnonceRepository.findAllByIdAnnonceAndEtat( idAnnonce, etatCandidature );
    }
}
