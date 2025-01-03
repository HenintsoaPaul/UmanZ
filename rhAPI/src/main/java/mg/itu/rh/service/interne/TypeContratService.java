package mg.itu.rh.service.interne;

import mg.itu.rh.entity.interne.TypeContrat;
import mg.itu.rh.repository.interne.TypeContratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeContratService {
    @Autowired
    private TypeContratRepository typeContratRepository;

    public TypeContrat findById(Long id){
        return typeContratRepository.findById(id).orElseThrow(()->new RuntimeException("Type de contrat non reconnue"));
    }
}
