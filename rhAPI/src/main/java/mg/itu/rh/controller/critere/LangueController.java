package mg.itu.rh.controller.critere;

import mg.itu.rh.entity.critere.Langue;
import mg.itu.rh.service.critere.LangueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( "/langues" )
public class LangueController {
    private final LangueService langueService;

    public LangueController( LangueService langueService ) {
        this.langueService = langueService;
    }

    @GetMapping
    public List<Langue> getAll() {
        return langueService.findAll();
    }
}
