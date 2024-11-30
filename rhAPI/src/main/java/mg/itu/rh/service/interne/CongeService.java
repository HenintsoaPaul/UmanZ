package mg.itu.rh.service.interne;

import mg.itu.rh.dto.interne.CongeDTO;
import mg.itu.rh.dto.interne.CongeTalentDTO;
import mg.itu.rh.entity.interne.Conge;
import mg.itu.rh.entity.interne.Contrat;
import mg.itu.rh.repository.interne.CongeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CongeService {
    @Autowired
    private CongeRepository congeRepository;

    @Autowired
    private ContratService contratService;

    public List<CongeTalentDTO> findAllValide(){
        return congeRepository.findAllWithTalent();
    }

    public Conge validate(Long idConge){
        Conge conge=congeRepository.findById(idConge).orElseThrow(()->new RuntimeException("Conge non reconnue"));
        conge.setDateValidation(LocalDate.now());
        return congeRepository.save(conge);
    }

    public List<CongeTalentDTO> findAllNonValide(){
        return congeRepository.findAllWithTalentNonValide();
    }

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
