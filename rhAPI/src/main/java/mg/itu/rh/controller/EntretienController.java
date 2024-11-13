package mg.itu.rh.controller;

import java.util.List;

import mg.itu.rh.dto.EntretienCandidatureDTO;
import mg.itu.rh.dto.EntretienValidationDTO;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonView;

import mg.itu.rh.entity.Entretien;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.EntretienService;


@RestController
@RequestMapping( "/entretien" )
public class EntretienController {
    private final EntretienService entretienService;

    public EntretienController( EntretienService entretienService ) {
        this.entretienService = entretienService;
    }

    @GetMapping( "/etat/{id}" )
    @JsonView( POV.Public.class )
    public List<Entretien> findByEtat( @PathVariable( "id" ) Long idEtat ) {
        return entretienService.findByEtat( idEtat );
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
    public void validate( @RequestBody EntretienValidationDTO validation ) {
        entretienService.valider( validation );
    }

    @PostMapping( "/deny" )
    public void deny( @RequestBody EntretienValidationDTO validation ) {
        entretienService.refuser( validation );
    }

    @PutMapping( "/" )
    public Entretien update( @RequestBody EntretienCandidatureDTO entretien )
            throws Exception {
        return entretienService.save( entretien );
    }

    @GetMapping( "/candidats" )
    public List<Entretien> getCandidaList() {
        return entretienService.getCandidaList();
    }
}
