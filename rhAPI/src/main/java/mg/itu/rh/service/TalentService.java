package mg.itu.rh.service;

import mg.itu.rh.entity.Talent;
import mg.itu.rh.repository.TalentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TalentService {
    @Autowired
    private TalentRepository talentRepository;

    public Talent findById(Long id){
        return talentRepository.findById(id).orElseThrow(()->new RuntimeException("Talent non reconnue"));
    }

    public List<Talent> findAll(){
        return talentRepository.findAll();
    }

    public Talent findByEmailAndPassword(String email,String password){
        return talentRepository.findByEmailAndPassword(email,password).orElse(null);
    }
}
