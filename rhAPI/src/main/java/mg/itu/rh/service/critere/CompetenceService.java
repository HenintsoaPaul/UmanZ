package mg.itu.rh.service.critere;

import mg.itu.rh.entity.critere.Competence;
import mg.itu.rh.entity.recrutement.CompetenceAnnonce;
import mg.itu.rh.repository.recrutement.CompetenceAnnonceRepository;
import mg.itu.rh.repository.recrutement.CompetenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetenceService {
    private final CompetenceRepository competenceRepository;
    private final CompetenceAnnonceRepository competenceAnnonceRepository;

    public CompetenceService( CompetenceRepository competenceRepository, CompetenceAnnonceRepository competenceAnnonceRepository ) {
        this.competenceRepository = competenceRepository;
        this.competenceAnnonceRepository = competenceAnnonceRepository;
    }

    public Competence findById( Long id ) {
        return this.competenceRepository.findById( id )
                .orElseThrow( () -> new IllegalArgumentException( "Competence not found" ) );
    }

    public List<Competence> findAll() {
        return competenceRepository.findAllCompetences();
    }

    public List<CompetenceAnnonce> findByIds( List<Long> ids ) {
        return competenceAnnonceRepository.findByIdIn( ids );
    }
}
