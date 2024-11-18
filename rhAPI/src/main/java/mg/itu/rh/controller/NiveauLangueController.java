package mg.itu.rh.controller;

import mg.itu.rh.entity.Langue;
import mg.itu.rh.entity.NiveauLangue;
import mg.itu.rh.service.LangueService;
import mg.itu.rh.service.NiveauLangueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( "/niveau_langues" )
public class NiveauLangueController {
    private final NiveauLangueService niveauLangueService;

    public NiveauLangueController( NiveauLangueService niveauLangueService ) {
        this.niveauLangueService = niveauLangueService;
    }

    @GetMapping
    public List<NiveauLangue> getAll() {
        return niveauLangueService.findAll();
    }
}
