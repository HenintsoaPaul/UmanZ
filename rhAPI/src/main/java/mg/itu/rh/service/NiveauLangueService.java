package mg.itu.rh.service;

import mg.itu.rh.entity.NiveauLangue;
import mg.itu.rh.repository.NiveauLangueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NiveauLangueService {
    private final NiveauLangueRepository niveauLangueRepository;

    public NiveauLangueService(NiveauLangueRepository niveauLangueRepository) {
        this.niveauLangueRepository = niveauLangueRepository;
    }

    public NiveauLangue findById(Long id){
        return niveauLangueRepository.findById(id).orElseThrow(()->new RuntimeException("Niveau de langue non reconnue"));
    }

    public List<NiveauLangue> findAll(){
        return niveauLangueRepository.findAll();
    }
}
