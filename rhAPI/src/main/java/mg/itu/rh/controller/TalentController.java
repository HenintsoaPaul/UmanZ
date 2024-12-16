package mg.itu.rh.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lombok.AllArgsConstructor;
import mg.itu.rh.dto.interne.DetailsFichePaieBruteDTO;
import mg.itu.rh.dto.interne.FicheDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import mg.itu.rh.dto.talent.TalentDTO;
import mg.itu.rh.entity.talent.Talent;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.talent.TalentService;

import mg.itu.rh.service.interne.*;

@AllArgsConstructor
@RestController
@RequestMapping( "/talents" )
public class TalentController {
    private PaieService paieService;
    private TalentService talentService;
    private ContratService contratService;

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

    @PostMapping
    @JsonView( POV.Public.class )
    public Talent save( @RequestBody TalentDTO talentDTO ) {
        return talentService.save( talentDTO );
    }

    @PostMapping( "/entretien" )
    @JsonView( POV.Public.class )
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
    @GetMapping("/{idTalent}/fiche-paie-tete")
    @JsonView(POV.Paie.class)
    public FicheDTO findFiche(@PathVariable(name = "idTalent") Long idTalent, @RequestParam(name = "annee") int annee, @RequestParam(name = "mois") int mois) {
        return paieService.findFiche(annee, mois, idTalent);
    }

    @GetMapping("/{idTalent}/fiche-paie-brute")
    public HashMap<String, List<DetailsFichePaieBruteDTO>> findDetailsFichePaieBrute(@PathVariable(name = "idTalent") Long idTalent, @RequestParam(name = "annee") int annee, @RequestParam(name = "mois") int mois) {
        HashMap<String, List<DetailsFichePaieBruteDTO>> details = new HashMap<>();

        List<DetailsFichePaieBruteDTO> detailsBrute = paieService.findDetailsFichePaieBrute(annee, mois, idTalent);
        details.put("brute", detailsBrute);

        if (!detailsBrute.isEmpty()) { // Ensure the list is not empty to avoid IndexOutOfBoundsException
            double salaireBrute = detailsBrute.get(detailsBrute.size() - 1).getMontant();
            details.put("retenue", paieService.findDetailsRetenue(salaireBrute));
        } else {
            // Handle the case where detailsBrute is empty
            details.put("retenue", new ArrayList<>()); // Empty list as a placeholder
        }

        return details;
    }
}
