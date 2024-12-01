package mg.itu.rh.controller.interne;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.rh.dto.interne.ContratDTO;
import mg.itu.rh.entity.interne.Contrat;
import mg.itu.rh.other.POV;

import java.util.List;

import mg.itu.rh.service.interne.ContratService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/contrats" )
public class ContratController {
    private final ContratService contratService;

    public ContratController( ContratService contratService ) {
        this.contratService = contratService;
    }

    @GetMapping
    @JsonView( POV.Public.class )
    public List<Contrat> findAll() {
        return contratService.findAll();
    }

    @GetMapping( "/{id}" )
    @JsonView( POV.Public.class )
    public Contrat findById( @PathVariable( "id" ) Long id ) {
        return contratService.findById( id );
    }

    @PostMapping
    @JsonView( POV.Public.class )
    public Contrat save( @RequestBody ContratDTO contratDTO ) {
        return contratService.save( contratDTO );
    }
}
