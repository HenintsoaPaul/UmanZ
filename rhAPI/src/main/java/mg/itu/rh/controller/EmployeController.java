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

    private EmployeService employeeService;
    private EmailService emailService;
    private PromotionService promotionService;

    @Autowired
    public EmployeController(EmployeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    @JsonView( POV.Public.class )
    public List<Employe> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<List<Employe>> getEmployeesByCategory(@PathVariable Long idCategories) {
        List<Employe> employees = employeeService.getEmployeesByCategory(idCategories);
        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{employeeId}/promotions")
    public ResponseEntity<List<Poste>> getPossiblePromotions(@PathVariable Integer employeeId) {
        List<Poste> promotions = promotionService.getPromotionsForEmployee(employeeId);
        return ResponseEntity.ok(promotions);
    }

    @PostMapping("/send-renvoi-email")
    public ResponseEntity<String> sendRenvoiEmail(@RequestBody RenvoiRequest renvoiRequest) {
        try {
            String subject = "Motif de Renvoi";
            String body = "<h1>Bonjour,</h1><p>Motif du renvoi : " + renvoiRequest.getMotif() + "</p>";

            emailService.sendEmail(renvoiRequest.getEmail(), subject, body);
            return ResponseEntity.ok("Email envoyé avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'envoi de l'email : " + e.getMessage());
        }
    }

}

