package mg.itu.rh.controller;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.rh.entity.Talent;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.TalentService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( "/talent" )
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
}
