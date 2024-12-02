package mg.itu.rh.controller.interne;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.rh.dto.interne.FicheDTO;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.interne.PaieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/paies" )
public class PaieController {

    private final PaieService paieService;

    public PaieController( PaieService paieService ) {
        this.paieService = paieService;
    }

    @GetMapping( "/talent/{idTalent}" )
    @JsonView( POV.Paie.class )
    public FicheDTO findFiche(
            @PathVariable( "idTalent" ) Long idTalent,
            @RequestParam( name = "annee" ) int annee,
            @RequestParam( name = "mois" ) int mois
    ) {
        return paieService.findFiche( annee, mois, idTalent );
    }

    @GetMapping
    @JsonView( POV.Paie.class )
    public List<FicheDTO> findFichesOnDate(
            @RequestParam( name = "year" ) int year,
            @RequestParam( name = "month" ) int month
    ) {
        return paieService.findAllFichePaiesOnDate( month, year );
    }
}
