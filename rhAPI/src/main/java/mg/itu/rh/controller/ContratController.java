package mg.itu.rh.controller;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import lombok.Data;
import mg.itu.rh.dto.ContratDTO;
import mg.itu.rh.dto.RuptureDTO;
import mg.itu.rh.entity.Contrat;
import mg.itu.rh.entity.Rupture;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.ContratService;
import mg.itu.rh.service.RuptureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contrat")
public class ContratController {
    @Autowired
    private ContratService contratService;

    /*
    * {
    *       "contrat":"Contrat mahafinaritra",
    *       "dateDebut":"2025-01-01",
    *       "dateFin":"2025-12-12",
    *       "salaireHoraire":125000,
    *       "nbJourSemaine":5,
    *       "nbJourCongeAn":20,
    *       "nbHeureJour":8,
    *       "idPoste":11,
    *       "idTalent":4,
    *       "idTypeContrat":3
    * }
    * */
    @PostMapping
    @JsonView(POV.Public.class)
    public Contrat save(@RequestBody ContratDTO contratDTO){
        return contratService.save(contratDTO);
    }


}
