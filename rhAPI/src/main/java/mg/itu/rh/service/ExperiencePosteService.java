package mg.itu.rh.service;

import mg.itu.rh.entity.Annonce;
import mg.itu.rh.entity.ExperiencePoste;
import mg.itu.rh.repository.ExperiencePosteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperiencePosteService {

    @Autowired
    private ExperiencePosteRepository experiencePosteRepository;

    public List<ExperiencePoste> findAll() {
        return experiencePosteRepository.findAll();
    }

    public List<ExperiencePoste> findByIds( List<Long> ids ) {
        return experiencePosteRepository.findByIdIn( ids );
    }

    public void saveAll( List<ExperiencePoste> experiencePostes, Annonce annonce ) {
        for ( ExperiencePoste experiencePoste : experiencePostes )
            save( experiencePoste, annonce );
    }

    public ExperiencePoste save( ExperiencePoste experiencePoste, Annonce annonce ) {
        experiencePoste.setAnnonce( annonce );
        return experiencePosteRepository.save( experiencePoste );
    }

    public List<ExperiencePoste> findAllByIdAnnonce( Long idAnnonce ) {
        Long etatCandidature = 2L;
        return experiencePosteRepository.findAllByIdAnnonceAndEtat( idAnnonce, etatCandidature );
    }
}
