package mg.itu.rh.service;

import mg.itu.rh.dto.CongeDTO;
import mg.itu.rh.entity.Conge;
import mg.itu.rh.entity.Contrat;
import mg.itu.rh.repository.CongeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CongeService {
    @Autowired
    private CongeRepository congeRepository;

    @Autowired
    private ContratService contratService;

    public List<Conge> findCongeByIdTalent(Long idTalent){
        return congeRepository.findCongeByIdTalent(idTalent);
    }

    public Conge save(Conge conge)throws Exception{
        this.controller(conge);
        return congeRepository.save(conge);
    }

    public Conge save(CongeDTO congeDTO)throws Exception{
        Contrat contrat=contratService.findActualContratByIdTalent(congeDTO.getIdTalent());
        Conge conge=new Conge();
        conge.setContrat(contrat);
        conge.setDateDebut(congeDTO.getDateDebut());
        conge.setMotif(congeDTO.getMotif());
        conge.setNbJour(congeDTO.getNbJour());
        return this.save(conge);
    }

    public void controller(Conge conge)throws RuntimeException{
        int nbJourPris=congeRepository.findNbJourCongePris(conge.getContrat().getIdContrat());
        if(conge.getContrat().getNbJourCongeAn()<nbJourPris+conge.getNbJour()){
            throw new RuntimeException("Demande de conge refuse pour cause de nombre de jour de conge pris superieur aux nombre de conge dans le contrat");
        }
    }
}
