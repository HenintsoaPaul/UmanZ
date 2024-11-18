package mg.itu.rh.service;

import mg.itu.rh.entity.Langue;
import mg.itu.rh.repository.LangueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LangueService {
    private final LangueRepository langueRepository;

    public LangueService(LangueRepository langueRepository) {
        this.langueRepository = langueRepository;
    }

    public Langue findById(Long id){
        return langueRepository.findById(id).orElseThrow(()->new RuntimeException("Langue non reconnue"));
    }

    public List<Langue> findAll(){
        return langueRepository.findAll();
    }
}
