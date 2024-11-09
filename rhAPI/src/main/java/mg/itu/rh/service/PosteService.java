package mg.itu.rh.service;

import mg.itu.rh.entity.Poste;
import mg.itu.rh.repository.PosteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PosteService {
    @Autowired
    private PosteRepository posteRepository;

    public Poste findById(Long id){
        return posteRepository.findById(id).orElseThrow(()->new RuntimeException("Poste non reconnue"));
    }

    public List<Poste> findAll() {
        return posteRepository.findAllPostes();
    }
}
