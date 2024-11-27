package mg.itu.rh.controller.interne;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.rh.dto.interne.ContratDTO;
import mg.itu.rh.entity.interne.Contrat;
import mg.itu.rh.other.POV;

import java.util.List;

import mg.itu.rh.service.interne.ContratService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/contrat" )
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

    /*
     * {
     *       "contrat":"Contrat mahafinaritra",
     *       "dateDebut":"2025-01-01",
     *       "dateFin":"2025-12-12",
     *       "salaireHoraire":125000,
     *       "nbJourSemaine":5,
     *       "nbJourCongeAn":20,
     *       "nbHeureJour":8,
     *       "idPoste":11,
     *       "idTalent":4,
     *       "idTypeContrat":3
     * }
     * */
    @PostMapping
    @JsonView( POV.Public.class )
    public Contrat save( @RequestBody ContratDTO contratDTO ) {
        return contratService.save( contratDTO );
    }
}