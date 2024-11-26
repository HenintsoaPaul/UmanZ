package mg.itu.rh.controller.critere;

import mg.itu.rh.entity.critere.NiveauLangue;
import mg.itu.rh.service.critere.NiveauLangueService;
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
