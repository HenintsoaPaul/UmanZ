package mg.itu.rh.service;

import mg.itu.rh.entity.NiveauLangue;
import mg.itu.rh.repository.NiveauLangueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NiveauLangueService {
    private final NiveauLangueRepository niveauLangueRepository;

    public NiveauLangueService(NiveauLangueRepository niveauLangueRepository) {
        this.niveauLangueRepository = niveauLangueRepository;
    }

    public NiveauLangue findById(Long id){
        return niveauLangueRepository.findById(id).orElseThrow(()->new RuntimeException("Niveau de langue non reconnue"));
    }
}
