package mg.itu.rh.service;

import mg.itu.rh.entity.Poste;
import mg.itu.rh.repository.PosteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService {

    @Autowired
    private PosteRepository posteRepository;

    // TODO : décommenter
//    public List<Poste> getPromotionsForEmployee(Integer idEmploye) {
//        return posteRepository.findPromotionsByEmployee(idEmploye);
//    }
}

