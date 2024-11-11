package mg.itu.rh.controller;

import com.fasterxml.jackson.annotation.JsonView;

import mg.itu.rh.dto.AnnonceDTO;
import mg.itu.rh.entity.Annonce;
import mg.itu.rh.entity.Talent;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.AnnonceService;
import mg.itu.rh.service.EntretienService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/annonce" )
public class AnnonceController {
    private final AnnonceService annonceService;
    private final EntretienService entretienService;

    public AnnonceController( AnnonceService as, EntretienService entretienService ) {
        this.annonceService = as;
        this.entretienService = entretienService;
    }

    @GetMapping( "/{id}" )
    @JsonView( POV.Public.class )
    public Annonce getAnnonce( @PathVariable( "id" ) Long id ) {
        return annonceService.findById( id );
    }

    @GetMapping( "/{id}/candidats" )
    @JsonView( POV.Public.class )
    public List<Talent> getCandidatsAnnonce( @PathVariable( "id" ) Long id ) {
        return entretienService.findAllCandidatsOfAnnonce( id );
    }

    @GetMapping( "/disponible" )
    @JsonView( POV.Public.class )
    public List<Annonce> getAnnonceDisponible() {
        return annonceService.findAnnonceAvailable();
    }

    @PostMapping()
    @JsonView( POV.Full.class )
    public Annonce save( @RequestBody AnnonceDTO annonceDTO ) {
        return annonceService.save( annonceDTO );
    }
}
