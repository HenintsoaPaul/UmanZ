package mg.itu.rh.service;

import mg.itu.rh.entity.Conge;
import mg.itu.rh.repository.CongeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CongeService {
    @Autowired
    private CongeRepository congeRepository;

    public List<Conge> findCongeByIdTalent(Long idTalent){
        return congeRepository.findCongeByIdTalent(idTalent);
    }
}
