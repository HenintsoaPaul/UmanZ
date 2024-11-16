package mg.itu.rh.service;

import jakarta.transaction.Transactional;
import mg.itu.rh.entity.AnnonceLangue;
import mg.itu.rh.repository.AnnonceLangueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnonceLangueService {
    @Autowired
    private AnnonceLangueRepository annonceLangueRepository;

    @Transactional
    public AnnonceLangue save(AnnonceLangue annonceLangue){
        return annonceLangueRepository.save(annonceLangue);
    }
}
