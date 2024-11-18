package mg.itu.rh.controller;

import mg.itu.rh.entity.NiveauDiplome;
import mg.itu.rh.service.NiveauDiplomeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( "/niveau_diplomes" )
public class NiveauDiplomeController {
    private final NiveauDiplomeService niveauDiplomeService;

    public NiveauDiplomeController( NiveauDiplomeService niveauDiplomeService ) {
        this.niveauDiplomeService = niveauDiplomeService;
    }

    @GetMapping
    public List<NiveauDiplome> getAll() {
        return niveauDiplomeService.findAll();
    }
}
