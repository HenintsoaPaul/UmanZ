package mg.itu.rh.controller;

import com.fasterxml.jackson.annotation.JsonView;

import mg.itu.rh.dto.AnnonceDTO;
import mg.itu.rh.entity.*;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/annonce" )
public class AnnonceController {
    private final AnnonceService annonceService;
    private final EntretienService entretienService;
    private final CompetenceAnnonceService competenceAnnonceService;
    private final ExperiencePosteService experiencePosteService;

    public AnnonceController( AnnonceService as, EntretienService es, CompetenceAnnonceService competenceAnnonceService, ExperiencePosteService experiencePosteService ) {
        this.annonceService = as;
        this.entretienService = es;
        this.competenceAnnonceService = competenceAnnonceService;
        this.experiencePosteService = experiencePosteService;
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

    @GetMapping( "/{id}/competences" )
    @JsonView( POV.Public.class )
    public List<CompetenceAnnonce> getCompetencesAnnonce( @PathVariable( "id" ) Long id ) {
        return competenceAnnonceService.findAllByIdAnnonce( id );
    }

    @GetMapping( "/{id}/experiences" )
    @JsonView( POV.Public.class )
    public List<ExperiencePoste> getExperiencesAnnonce( @PathVariable( "id" ) Long id ) {
        return experiencePosteService.findAllByIdAnnonce( id );
    }

    @GetMapping( "/disponible/{id}" )
    @JsonView( POV.Public.class )
    public List<Compatibilite> getAnnonceDisponible(@PathVariable("id")Long id) {
        return annonceService.findAnnonceAvailable(id);
    }

    @PostMapping()
    @JsonView( POV.Full.class )
    public Annonce save( @RequestBody AnnonceDTO annonceDTO ) {
        return annonceService.save( annonceDTO );
    }
}
