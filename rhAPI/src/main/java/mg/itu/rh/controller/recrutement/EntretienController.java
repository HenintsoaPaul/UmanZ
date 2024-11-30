package mg.itu.rh.controller.recrutement;

import java.util.List;

import mg.itu.rh.dto.recrutement.EntretienCandidatureDTO;
import mg.itu.rh.dto.recrutement.EntretienValidationDTO;
import mg.itu.rh.service.recrutement.EntretienService;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonView;

import mg.itu.rh.entity.recrutement.Entretien;
import mg.itu.rh.other.POV;

@RestController
@RequestMapping( "/entretien" )
public class EntretienController {
    private final EntretienService entretienService;

    public EntretienController( EntretienService entretienService ) {
        this.entretienService = entretienService;
    }

    @GetMapping( "/{id}" )
    @JsonView( POV.Public.class )
    public Entretien findById( @PathVariable( "id" ) Long id ) {
        return entretienService.findById( id );
    }

    @GetMapping( "/etat/{id}" )
    @JsonView( POV.Public.class )
    public List<Entretien> findByEtat( @PathVariable( "id" ) Long idEtat ) {
        return entretienService.findByEtat( idEtat );
    }

    @GetMapping( "/candidats" )
    @JsonView( POV.Public.class )
    public List<Entretien> getCandidaList() {
        return entretienService.getCandidaList();
    }

    /*
     * { "idTalent":3, "idAnnonce":1 }
     * */
    @PostMapping( "/candiat" )
    @JsonView( POV.Public.class )
    private Entretien saveCandidat( @RequestBody EntretienCandidatureDTO entretienCandidat ) {
        return entretienService.saveCandidat( entretienCandidat );
    }

    @PostMapping( "/validate" )
    @JsonView( POV.Full.class )
    public Entretien validate( @RequestBody EntretienValidationDTO validation ) {
        return entretienService.valider( validation );
    }

    @PostMapping( "/deny" )
    @JsonView( POV.Full.class )
    public Entretien deny( @RequestBody EntretienValidationDTO validation ) {
        return entretienService.refuser( validation );
    }

    @PutMapping( "/" )
    public Entretien update( @RequestBody EntretienCandidatureDTO entretien )
            throws Exception {
        return entretienService.save( entretien );
    }
}
