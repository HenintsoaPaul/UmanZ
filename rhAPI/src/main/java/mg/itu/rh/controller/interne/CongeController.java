package mg.itu.rh.controller.interne;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.rh.dto.interne.CongeDTO;
import mg.itu.rh.dto.interne.CongeTalentDTO;
import mg.itu.rh.entity.interne.Conge;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.interne.CongeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/conges" )
public class CongeController {
    private final CongeService congeService;

    public CongeController( CongeService congeService ) {
        this.congeService = congeService;
    }

    @GetMapping
    @JsonView( POV.Full.class )
    public List<CongeTalentDTO> findAll() {
        return congeService.findAllValide();
    }

    @GetMapping( "/non_valide" )
    @JsonView( POV.Full.class )
    public List<CongeTalentDTO> findALlNonValide() {
        return congeService.findAllNonValide();
    }

    @GetMapping( "/validate/{idConge}" )
    @JsonView( POV.Full.class )
    public Conge validate( @PathVariable( "idConge" ) Long idConge ) {
        return congeService.validate( idConge );
    }

    @GetMapping( "/talent/{id}" )
    @JsonView( POV.Public.class )
    public List<Conge> getCongeByTalent( @PathVariable( "id" ) Long id ) {
        return congeService.findCongeByIdTalent( id );
    }

    @GetMapping( "/paye/{id}" )
    @JsonView( POV.Public.class )
    public int getSoldeCongePayeByContrat( @PathVariable( "id" ) Long idContrat ) {
        return congeService.getNbCongePayePrisByIdContrat( idContrat );
    }

    @JsonView( POV.Public.class )
    @PostMapping
    public Conge save( @RequestBody CongeDTO congeDTO )
            throws Exception {
        return congeService.save( congeDTO );
    }
}
