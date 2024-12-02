package mg.itu.rh.service.interne;

import mg.itu.rh.repository.PosteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionService {

    @Autowired
    private PosteRepository posteRepository;

//    public List<Poste> getPromotionsForEmployee(Integer idTalent) {
//        return posteRepository.findPromotionsByTalent(idTalent);
//    }
}

