package mg.itu.rh.controller;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.rh.dto.interne.FicheDTO;
import mg.itu.rh.dto.talent.TalentDTO;
import mg.itu.rh.entity.talent.Talent;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.interne.ContratService;
import mg.itu.rh.service.talent.TalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/talents" )
public class TalentController {
    private final TalentService talentService;
    private final ContratService contratService;

    public TalentController(TalentService talentService, ContratService contratService) {
        this.talentService = talentService;
        this.contratService = contratService;
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
    @JsonView( POV.Public.class )
    public Talent login( @Param( "email" ) String email, @Param( "password" ) String password ) {
        return talentService.findByEmailAndPassword( email, password );
    }

    @PostMapping
    @JsonView( POV.Public.class )
    public Talent create( @RequestBody TalentDTO talentDTO ) {
        return talentService.save( talentDTO );
    }

    @PostMapping( "/entretien" )
    public String prendreEntretien( @RequestParam String email ) {
        talentService.prendreEntretien( email );
        return "Entretien pris et notification envoyée à " + email;
    }

    @PostMapping( "/rejet" )
    public String rejeterCandidat( @RequestParam String email ) {
        talentService.rejetCandidat( email );
        return "Candidat rejeté et notification envoyée à " + email;
    }

    /*
    *
    {
            "nomPrenom": "Leroy Paul",
            "matricule": 5,
            "fonction": "Support technique",
            "dateEmbauche": "2024-01-01",
            "anciennete": "9 mois et 30 jours",
            "classification": "Dirigeants",
            "idCnaps": "CNP354632",
            "salaire": 4387.5,
            "tauxJournalier": 189,
            "tauxHoraire": 25,
            "indice": 141
     }
    * */
    @GetMapping("/fiche_paie")
    @JsonView(POV.Public.class)
    public FicheDTO findFiche(@RequestParam(name = "idTalent")Long idTalent,@RequestParam(name = "annee")int annee,@RequestParam(name = "mois")int mois){
        return contratService.findFiche(annee,mois,idTalent);
    }
}
