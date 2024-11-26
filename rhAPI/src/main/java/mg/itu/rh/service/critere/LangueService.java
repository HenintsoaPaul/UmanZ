package mg.itu.rh.service.critere;

import mg.itu.rh.entity.critere.Langue;
import mg.itu.rh.repository.critere.LangueRepository;
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
