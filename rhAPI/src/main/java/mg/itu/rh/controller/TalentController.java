package mg.itu.rh.controller;

import java.util.List;

import lombok.AllArgsConstructor;
import mg.itu.rh.service.interne.ContratService;
import mg.itu.rh.service.interne.PromotionService;
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

@AllArgsConstructor
@RestController
@RequestMapping( "/talents" )
public class TalentController {
    private TalentService talentService;
    private ContratService contratService;
    private PromotionService promotionService;

//    @GetMapping( "/by-category/{idCategories}" )
//    @JsonView( POV.Public.class )
//    public ResponseEntity<List<Talent>> getEmployeesByCategory( @PathVariable Long idCategories ) {
//        List<Talent> employees = talentService.getEmployeesByCategory( idCategories );
//        if ( employees.isEmpty() ) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok( employees );
//    }
//
//    @GetMapping( "/{employeeId}/promotions" )
//    @JsonView( POV.Public.class )
//    public ResponseEntity<List<Poste>> getPossiblePromotions( @PathVariable Integer employeeId ) {
//        List<Poste> promotions = promotionService.getPromotionsForEmployee( employeeId );
//        return ResponseEntity.ok( promotions );
//    }

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
}
