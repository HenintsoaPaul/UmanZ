package mg.itu.rh.service;

import mg.itu.rh.entity.Talent;
import mg.itu.rh.repository.TalentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TalentService {
    @Autowired
    private TalentRepository talentRepository;

    public Talent findById(Long id){
        return talentRepository.findById(id).orElseThrow(()->new RuntimeException("Talent non reconnue"));
    }
}
