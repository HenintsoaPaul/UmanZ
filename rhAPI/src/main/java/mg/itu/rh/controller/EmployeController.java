package mg.itu.rh.controller;

import mg.itu.rh.entity.*;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/employees")
public class EmployeController {

    private final EmployeService employeeService;
    private final EmailService emailService;
    private final PromotionService promotionService;

    public EmployeController(EmployeService employeeService, EmailService emailService, PromotionService promotionService) {
        this.employeeService = employeeService;
        this.emailService = emailService;
        this.promotionService = promotionService;
    }

    @GetMapping("/all")
    @JsonView( POV.Public.class )
    public List<Employe> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // TODO: Décommenter le endpoint une fois que le fonctionnement de l'envoi d'email garanti
//    @GetMapping("/by-category/{idCategory}")
//    public ResponseEntity<List<Employe>> getEmployeesByCategory(@PathVariable Long idCategory) {
//        List<Employe> employees = employeeService.getEmployeesByCategory(idCategory);
//        if (employees.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(employees);
//    }

    // TODO: Décommenter le endpoint une fois que le fonctionnement de l'envoi d'email garanti
//    @GetMapping("/{employeeId}/promotions")
//    public ResponseEntity<List<Poste>> getPossiblePromotions(@PathVariable Integer employeeId) {
//        List<Poste> promotions = promotionService.getPromotionsForEmployee(employeeId);
//        return ResponseEntity.ok(promotions);
//    }

    // TODO: Décommenter le endpoint une fois que le fonctionnement de l'envoi d'email garanti
//    @PostMapping("/send-renvoi-email")
//    public ResponseEntity<String> sendRenvoiEmail(@RequestBody RenvoiRequest renvoiRequest) {
//        try {
//            String subject = "Motif de Renvoi";
//            String body = "<h1>Bonjour,</h1><p>Motif du renvoi : " + renvoiRequest.getMotif() + "</p>";
//
//            emailService.sendEmail(renvoiRequest.getEmail(), subject, body);
//            return ResponseEntity.ok("Email envoyé avec succès.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'envoi de l'email : " + e.getMessage());
//        }
//    }

}

