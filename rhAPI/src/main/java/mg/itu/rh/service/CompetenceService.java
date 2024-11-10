package mg.itu.rh.service;

import mg.itu.rh.entity.Competence;
import mg.itu.rh.entity.CompetenceAnnonce;
import mg.itu.rh.repository.CompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetenceService {
    @Autowired
    private CompetenceRepository competenceRepository;

    public List<Competence> findAll() {
        return competenceRepository.findAllCompetences();
    }

    public List<CompetenceAnnonce> findByIds(List<Long> ids) {
        return competenceRepository.findAllById(ids);
    }
}
