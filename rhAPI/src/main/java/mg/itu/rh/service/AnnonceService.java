package mg.itu.rh.service;

import mg.itu.rh.entity.Annonce;
import mg.itu.rh.repository.AnnonceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnonceService {
    @Autowired
    private AnnonceRepository annonceRepository;

    public List<Annonce> findAnnonceAvailable(){
        return annonceRepository.findAnnonceAvailable();
    }

    public Annonce findAnnonceById(Long id)throws Exception{
        return annonceRepository.findById(id).orElseThrow(()->new RuntimeException("Annonce non reconnue"));
    }
}
