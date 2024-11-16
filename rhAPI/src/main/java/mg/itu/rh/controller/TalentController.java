package mg.itu.rh.controller;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.rh.dto.TalentDTO;
import mg.itu.rh.entity.Talent;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.TalentService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/talents" )
public class TalentController {
    private final TalentService talentService;

    public TalentController( TalentService talentService ) {
        this.talentService = talentService;
    }

    @GetMapping
    @JsonView( POV.Public.class )
    public List<Talent> findAll() {
        return talentService.findAll();
    }

    @GetMapping( "/{id}" )
    @JsonView( POV.Public.class )
    public Talent findById( @PathVariable( "id" ) Long id ) {
        return talentService.findById( id );
    }

    @GetMapping( "/users" )
    public Talent login( @Param( "email" ) String email, @Param( "password" ) String password ) {
        return talentService.findByEmailAndPassword( email, password );
    }

    @PostMapping
    @JsonView( POV.Public.class )
    public Talent create( @RequestBody TalentDTO talentDTO ) {
        return talentService.save( talentDTO );
    }
}
