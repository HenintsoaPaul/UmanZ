package mg.itu.rh.service;

import mg.itu.rh.entity.Diplome;
import mg.itu.rh.repository.DiplomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiplomeService {
    @Autowired
    private DiplomeRepository diplomeRepository;

    public Diplome findByIdDiplome(Long id)throws RuntimeException{
        return diplomeRepository.findById(id).orElseThrow(()->new RuntimeException("Diplome non reconnue"));
    }
}
