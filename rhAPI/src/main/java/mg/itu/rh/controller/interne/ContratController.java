package mg.itu.rh.controller.interne;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.rh.dto.interne.ContratDTO;
import mg.itu.rh.entity.interne.Contrat;
import mg.itu.rh.other.POV;

import java.util.List;

import mg.itu.rh.repository.interne.ContratRepository;
import mg.itu.rh.service.interne.ContratService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/contrats" )
public class ContratController {
    private final ContratService contratService;
    private final ContratRepository contratRepository;

    public ContratController( ContratService contratService, ContratRepository contratRepository ) {
        this.contratService = contratService;
        this.contratRepository = contratRepository;
    }

    @GetMapping
    @JsonView( POV.Public.class )
    public List<Contrat> findAll() {
        return contratRepository.findAll();
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

    @GetMapping("/now")
    @JsonView(POV.Public.class)
    public List<Contrat> findContrat(){
        return contratService.findAllContratNow();
    }
}
