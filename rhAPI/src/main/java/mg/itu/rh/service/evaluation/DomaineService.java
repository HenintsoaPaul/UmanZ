package mg.itu.rh.service.evaluation;

import mg.itu.rh.entity.evaluation.Domaine;
import mg.itu.rh.repository.evaluation.DomaineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomaineService {
    private final DomaineRepository domaineRepository;

    public DomaineService(DomaineRepository domaineRepository) {
        this.domaineRepository = domaineRepository;
    }

    public List<Domaine> findAll(){
        return domaineRepository.findAll();
    }
}
