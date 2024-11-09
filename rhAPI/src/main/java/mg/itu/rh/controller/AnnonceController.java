package mg.itu.rh.controller;

import com.fasterxml.jackson.annotation.JsonView;

import mg.itu.rh.dto.AnnonceDTO;
import mg.itu.rh.entity.Annonce;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.AnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/annonce")
public class AnnonceController {
    @Autowired
    private AnnonceService annonceService;

    @GetMapping("/disponible")
    @JsonView(POV.Full.class)
    public List<Annonce> getAnnonceDisponible(){
        return annonceService.findAnnonceAvailable();
    }

    @PostMapping("/save")
    @JsonView(POV.Full.class)
    public Annonce save(@RequestBody AnnonceDTO annonceDTO) {
        return annonceService.save(annonceDTO);
    }
}
