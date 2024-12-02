package mg.itu.rh.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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

import mg.itu.rh.entity.interne.RenvoiRequest;
import mg.itu.rh.service.interne.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@RestController
@RequestMapping( "/talents" )
public class TalentController {
    private PaieService paieService;
    private TalentService talentService;
    private EmailService emailService;
    private ContratService contratService;
    private PromotionService promotionService;

    @PostMapping( "/ruptureEmail" )
    public ResponseEntity<String> ruptureEmail( @RequestBody RenvoiRequest renvoiRequest ) {
        System.out.println( "Motif: " + renvoiRequest.getMotif() );
        System.out.println( "Date: " + renvoiRequest.getDate() );
        try {
            String toEmail = "gestionsender@gmail.com";
            String subject = "Nouvelle demande de rupture";
            String body = String.format(
                    "<h1>Demande de rupture</h1>" +
                            "<p><strong>Motif :</strong> %s</p>" +
                            "<p><strong>Date :</strong> %s</p>",
                    renvoiRequest.getMotif(),
                    renvoiRequest.getDate()
            );

            emailService.sendEmail( toEmail, subject, body );
            return ResponseEntity.ok( "Email envoyé avec succès." );
        } catch ( Exception e ) {
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR )
                    .body( "Erreur lors de l'envoi de l'email : " + e.getMessage() );
        }
    }

    @GetMapping
    @JsonView( POV.Public.class )
    public List<Talent> findAll() {
        return talentService.findAll();
    }

//    @GetMapping("/by-category/{idCategories}")
//    @JsonView( POV.Public.class )
//    public ResponseEntity<List<Talent>> getEmployeesByCategory(@PathVariable Long idCategories) {
//        List<Talent> employees = talentService.getEmployeesByCategory(idCategories);
//        if (employees.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(employees);
//    }

//    @GetMapping("/{employeeId}/promotions")
//    @JsonView( POV.Public.class )
//    public ResponseEntity<List<Poste>> getPossiblePromotions(@PathVariable Integer employeeId) {
//        List<Poste> promotions = promotionService.getPromotionsForEmployee(employeeId);
//        return ResponseEntity.ok(promotions);
//    }

    @PostMapping( "/send-renvoi-email" )
    @JsonView( POV.Public.class )
    public ResponseEntity<String> sendRenvoiEmail( @RequestBody RenvoiRequest renvoiRequest ) {
        try {
            String subject = "Motif de Renvoi";
            String body = "<h1>Bonjour,</h1><p>Motif du renvoi : " + renvoiRequest.getMotif() + "</p>";

//            emailService.sendEmail(renvoiRequest.getEmail(), subject, body);
            return ResponseEntity.ok( "Email envoyé avec succès." );
        } catch ( Exception e ) {
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( "Erreur lors de l'envoi de l'email : " + e.getMessage() );
        }
    }

    @GetMapping( "/{id}" )
    @JsonView( POV.Public.class )
    public Talent findById( @PathVariable( "id" ) Long id ) {
        return talentService.findById( id );
    }

    @PostMapping
    @JsonView( POV.Public.class )
    public Talent create( @RequestBody TalentDTO talentDTO ) {
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
        return contratService.findFiche(annee, mois, idTalent);
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
