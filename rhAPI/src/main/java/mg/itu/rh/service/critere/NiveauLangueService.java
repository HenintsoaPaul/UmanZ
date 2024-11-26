package mg.itu.rh.service.critere;

import mg.itu.rh.entity.critere.NiveauLangue;
import mg.itu.rh.repository.critere.NiveauLangueRepository;
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
