package mg.itu.rh.controller.recrutement;

import com.fasterxml.jackson.annotation.JsonView;

import mg.itu.rh.dto.recrutement.AnnonceDTO;
import mg.itu.rh.entity.recrutement.Annonce;
import mg.itu.rh.entity.recrutement.CompetencePoste;
import mg.itu.rh.entity.recrutement.ExperiencePoste;
import mg.itu.rh.entity.talent.Talent;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.recrutement.AnnonceService;
import mg.itu.rh.service.recrutement.CompetencePosteService;
import mg.itu.rh.service.recrutement.EntretienService;
import mg.itu.rh.service.recrutement.ExperiencePosteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/annonce" )
public class AnnonceController {
    private final AnnonceService annonceService;
    private final EntretienService entretienService;
    private final CompetencePosteService competencePosteService;
    private final ExperiencePosteService experiencePosteService;

    public AnnonceController(AnnonceService as, EntretienService es, CompetencePosteService competenceAnnonceService, ExperiencePosteService experiencePosteService ) {
        this.annonceService = as;
        this.entretienService = es;
        this.competencePosteService = competenceAnnonceService;
        this.experiencePosteService = experiencePosteService;
    }

    @GetMapping( "/{id}" )
    @JsonView( POV.Public.class )
    public Annonce getAnnonce(@PathVariable( "id" ) Long id ) {
        return annonceService.findById( id );
    }

    @GetMapping( "/{id}/candidats" )
    @JsonView( POV.Public.class )
    public List<Talent> getCandidatsAnnonce(@PathVariable( "id" ) Long id ) {
        return entretienService.findAllCandidatsOfAnnonce( id );
    }

    @GetMapping( "/{id}/competences" )
    @JsonView( POV.Public.class )
    public List<CompetencePoste> getCompetencesAnnonce(@PathVariable( "id" ) Long id ) {
        return competencePosteService.findAllByIdAnnonce( id );
    }

    @GetMapping( "/{id}/experiences" )
    @JsonView( POV.Public.class )
    public List<ExperiencePoste> getExperiencesAnnonce(@PathVariable( "id" ) Long id ) {
        return experiencePosteService.findAllByIdAnnonce( id );
    }

    @GetMapping( "/disponible" )
    @JsonView( POV.Public.class )
    public List<Annonce> getAnnonceDisponible() {
        return annonceService.findAllDisponible();
    }

    @GetMapping( "/disponible/{id}" )
    @JsonView( POV.Public.class )
    public List<Annonce> getAnnonceDisponible(@PathVariable("id")Long id) {
        return annonceService.findAnnonceAvailable(id);
    }

    @PostMapping()
    @JsonView( POV.Full.class )
    public Annonce save( @RequestBody AnnonceDTO annonceDTO ) {
        return annonceService.save( annonceDTO );
    }
}
