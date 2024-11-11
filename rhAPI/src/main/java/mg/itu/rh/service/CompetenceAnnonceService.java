package mg.itu.rh.service;

import mg.itu.rh.entity.Annonce;
import mg.itu.rh.entity.CompetenceAnnonce;
import mg.itu.rh.repository.CompetenceAnnonceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetenceAnnonceService {
    @Autowired
    private CompetenceAnnonceRepository competenceAnnonceRepository;

    public List<CompetenceAnnonce> findByIds( List<Long> ids ) {
        return competenceAnnonceRepository.findByIdIn( ids );
    }

    public void saveAll( List<CompetenceAnnonce> competences, Annonce annonce ) {
        for ( CompetenceAnnonce competence : competences )
            save( competence, annonce );
    }

    private CompetenceAnnonce save( CompetenceAnnonce competence, Annonce annonce ) {
        competence.setAnnonce( annonce );
        return competenceAnnonceRepository.save( competence );
    }
}
