package mg.itu.rh.controller;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.rh.dto.ParticipationDTO;
import mg.itu.rh.entity.Formation;
import mg.itu.rh.entity.Participation;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.FormationService;
import mg.itu.rh.service.ParticipationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/participations" )
public class ParticipationController {
    private final ParticipationService participationService;
    private final FormationService formationService;

    public ParticipationController( ParticipationService participationService, FormationService formationService ) {
        this.participationService = participationService;
        this.formationService = formationService;
    }

    @GetMapping
    @JsonView( POV.Public.class )
    public List<Participation> findAll() {
        return participationService.findAll();
    }

    @GetMapping( "/{id}" )
    @JsonView( POV.Public.class )
    public List<Participation> findAllByFormation( @PathVariable( "id" ) Long id ) {
        return participationService.findAllByFormation( id );
    }

    @PostMapping
    @JsonView( POV.Public.class )
    public Participation save( @RequestBody ParticipationDTO participationDTO )
            throws Exception {
        Formation formation = this.formationService.findById( participationDTO.getIdFormation() );
        return participationService.save( participationDTO, formation );
    }
}
