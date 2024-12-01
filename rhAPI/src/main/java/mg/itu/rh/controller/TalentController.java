package mg.itu.rh.controller;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.rh.dto.talent.TalentDTO;
import mg.itu.rh.entity.interne.RenvoiRequest;
import mg.itu.rh.entity.talent.Talent;
import mg.itu.rh.service.interne.*;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.talent.TalentService;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/talents" )
public class TalentController {
    private final TalentService talentService;

    // @Autowired
    // private final EmailService emailService;

    // private final PromotionService promotionService;

    public TalentController(
            TalentService talentService
//            EmailService emailService,
//            PromotionService promotionService
    ) {
        this.talentService = talentService;
//        this.emailService = emailService;
//        this.promotionService = promotionService;
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

    @PostMapping("/send-renvoi-email")
    @JsonView( POV.Public.class )
    public ResponseEntity<String> sendRenvoiEmail(@RequestBody RenvoiRequest renvoiRequest) {
        try {
            String subject = "Motif de Renvoi";
            String body = "<h1>Bonjour,</h1><p>Motif du renvoi : " + renvoiRequest.getMotif() + "</p>";

//            emailService.sendEmail(renvoiRequest.getEmail(), subject, body);
            return ResponseEntity.ok("Email envoyé avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'envoi de l'email : " + e.getMessage());
        }
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
}
