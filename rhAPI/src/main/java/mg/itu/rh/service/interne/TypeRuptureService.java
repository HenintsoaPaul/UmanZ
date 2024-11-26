package mg.itu.rh.service.interne;

import mg.itu.rh.entity.interne.TypeRupture;
import mg.itu.rh.repository.interne.TypeRuptureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeRuptureService {
    @Autowired
    private TypeRuptureRepository typeRuptureRepository;

    public TypeRupture findById(Long idTypeRupture){
        return typeRuptureRepository.findById(idTypeRupture).orElseThrow(()->new RuntimeException("Type de rupture non reconnue"));
    }

    public List<TypeRupture> findAll(){
        return typeRuptureRepository.findAll();
    }
}
