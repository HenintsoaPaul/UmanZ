package mg.itu.rh.service;

import mg.itu.rh.entity.CompetenceAnnonce;
import mg.itu.rh.repository.CompetenceAnnonceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetenceAnnonceService {
    @Autowired
    private CompetenceAnnonceRepository competenceAnnonceRepository;

    public List<CompetenceAnnonce> findByIds(List<Long> ids) {
        return competenceAnnonceRepository.findByIdIn(ids);
    }
}
