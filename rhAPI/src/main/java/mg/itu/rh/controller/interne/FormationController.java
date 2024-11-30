package mg.itu.rh.controller.interne;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.rh.dto.interne.FormationDTO;
import mg.itu.rh.entity.interne.Formation;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.interne.FormationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/formations" )
public class FormationController {
    private final FormationService formationService;

    public FormationController( FormationService formationService ) {
        this.formationService = formationService;
    }

    @PostMapping
    public Formation create( @RequestBody FormationDTO formationDTO ) {
        return formationService.save( formationDTO );
    }

    @GetMapping
    @JsonView( POV.Public.class )
    public List<Formation> getAll() {
        return formationService.findAll();
    }

    @GetMapping( "/disponible" )
    @JsonView( POV.Public.class )
    public List<Formation> getAllDisponible() {
        return formationService.findAllDisponible();
    }

    @GetMapping( "/{id}" )
    @JsonView( POV.Public.class )
    public Formation findById( @PathVariable( "id" ) Long id ) {
        return formationService.findById( id );
    }

    @GetMapping( "/{id}/finir" )
    @JsonView( POV.Public.class )
    public Formation terminate( @PathVariable( "id" ) Long id ) {
        return formationService.finir( id );
    }
}
