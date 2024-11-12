package mg.itu.rh.service;

import mg.itu.rh.dto.CompetenceAnnonceDTO;
import mg.itu.rh.entity.Annonce;
import mg.itu.rh.entity.CompetenceAnnonce;
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

    public List<CompetenceAnnonce> findByIds( List<Long> ids ) {
        return competenceAnnonceRepository.findByIdIn( ids );
    }

    public void saveAll( List<CompetenceAnnonce> competences, Annonce annonce ) {
        for ( CompetenceAnnonce competence : competences )
            save( competence, annonce );
    }

    public void saveAllFromDTO( List<CompetenceAnnonceDTO> competences, Annonce annonce ) {
        for ( CompetenceAnnonceDTO competence : competences )
            save( competence, annonce );
    }

    private CompetenceAnnonce save( CompetenceAnnonce competence, Annonce annonce ) {
        competence.setAnnonce( annonce );
        return competenceAnnonceRepository.save( competence );
    }

    private CompetenceAnnonce save( CompetenceAnnonceDTO dto, Annonce annonce ) {
        CompetenceAnnonce ca = dto.getCompetenceAnnonce( competenceService );
        return this.save( ca, annonce );
    }

    public List<CompetenceAnnonce> findAllByIdAnnonce( Long idAnnonce ) {
        Long etatCandidature = 2L;
        return competenceAnnonceRepository.findAllByIdAnnonceAndEtat( idAnnonce, etatCandidature );
    }
}
