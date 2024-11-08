package mg.itu.rh.controller;

import java.util.List;

import mg.itu.rh.dto.EntretienDTO;
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

    @GetMapping("/validate/{id}")
    public void validate(@PathVariable("id") Long idEntretien) {
        entretienService.valider(idEntretien);
    }

    @PutMapping("/")
    public Entretien update(@RequestBody EntretienDTO entretien){
        return entretienService.save(entretien);
    }
    
}
