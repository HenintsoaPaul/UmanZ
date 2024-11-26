package mg.itu.rh.service.critere;

import mg.itu.rh.entity.critere.Competence;
import mg.itu.rh.entity.recrutement.CompetencePoste;
import mg.itu.rh.repository.recrutement.CompetencePosteRepository;
import mg.itu.rh.repository.recrutement.CompetenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetenceService {
    private final CompetenceRepository competenceRepository;
    private final CompetencePosteRepository competenceAnnonceRepository;

    public CompetenceService( CompetenceRepository competenceRepository, CompetencePosteRepository competenceAnnonceRepository ) {
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

    public List<CompetencePoste> findByIds(List<Long> ids ) {
        return competenceAnnonceRepository.findByIdIn( ids );
    }
}
