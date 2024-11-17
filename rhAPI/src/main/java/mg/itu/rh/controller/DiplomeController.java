package mg.itu.rh.controller;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.rh.entity.Diplome;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.DiplomeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( "/diplomes" )
public class DiplomeController {
    private final DiplomeService diplomeService;

    public DiplomeController( DiplomeService diplomeService ) {
        this.diplomeService = diplomeService;
    }

    @GetMapping
    @JsonView({ POV.Public.class})
    public List<Diplome> getAll() {
        return diplomeService.findAll();
    }
}
