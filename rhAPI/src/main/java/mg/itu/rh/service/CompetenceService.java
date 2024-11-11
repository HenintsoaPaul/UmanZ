package mg.itu.rh.service;

import mg.itu.rh.entity.Competence;
import mg.itu.rh.entity.CompetenceAnnonce;
import mg.itu.rh.repository.CompetenceAnnonceRepository;
import mg.itu.rh.repository.CompetenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetenceService {
    private final EntretienService entretienService;
    private final CompetenceRepository competenceRepository;
    private final CompetenceAnnonceRepository competenceAnnonceRepository;

    public CompetenceService( EntretienService es, CompetenceRepository competenceRepository, CompetenceAnnonceRepository competenceAnnonceRepository ) {
        this.entretienService = es;
        this.competenceRepository = competenceRepository;
        this.competenceAnnonceRepository = competenceAnnonceRepository;
    }

    public List<Competence> findAll() {
        return competenceRepository.findAllCompetences();
    }

    public List<CompetenceAnnonce> findByIds(List<Long> ids) {
        return competenceAnnonceRepository.findByIdIn(ids);
    }
}
