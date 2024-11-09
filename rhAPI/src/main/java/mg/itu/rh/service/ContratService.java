package mg.itu.rh.service;

import mg.itu.rh.entity.Contrat;
import mg.itu.rh.repository.ContratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContratService {

    @Autowired
    private ContratRepository contratRepository;

    public Contrat findActualContratByIdTalent(Long idTalent){
        return contratRepository.findActualContratByIdTalent(idTalent).orElseThrow(()->new RuntimeException("Cette personne n'est pas un employe"));
    }
}
