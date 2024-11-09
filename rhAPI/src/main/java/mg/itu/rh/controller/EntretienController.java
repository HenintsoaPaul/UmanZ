package mg.itu.rh.controller;

import java.util.List;

import mg.itu.rh.dto.EntretienCandidatureDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonView;

import mg.itu.rh.entity.Entretien;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.EntretienService;



@RestController
@RequestMapping("/entretien")
public class EntretienController {

    @Autowired
    private EntretienService entretienService;

    @GetMapping("/etat/{id}")
    @JsonView(POV.Public.class)
    public List<Entretien> findByEtat(@PathVariable("id")Long idEtat) {
        return entretienService.findByEtat(idEtat);
    }

    /*
    * {
    *       "idTalent":3,
    *       "idAnnonce":1
    * }
    * */
    @PostMapping("/candiat")
    @JsonView(POV.Public.class)
    private Entretien saveCandidat(@RequestBody EntretienCandidatureDTO entretienCandidat)throws Exception{
        return entretienService.saveCandidat(entretienCandidat);
    }

    @GetMapping("/validate/{id}")
    public void validate(@PathVariable("id") Long idEntretien) {
        entretienService.valider(idEntretien);
    }

    @PutMapping("/")
    public Entretien update(@RequestBody EntretienCandidatureDTO entretien)throws Exception{
        return entretienService.save(entretien);
    }
    
}
