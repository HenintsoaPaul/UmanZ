package mg.itu.rh.controller;

import mg.itu.rh.entity.Talent;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.TalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

@RestController
@RequestMapping("/talent")
public class TalentController {
    @Autowired
    private TalentService talentService;

    @GetMapping("/users")
    public Talent login(@Param("email") String email,@Param("password") String password){
        return talentService.findByEmailAndPassword(email,password);
    }

    @PostMapping("/entretien")
    public String prendreEntretien(@RequestParam String email) {
        talentService.prendreEntretien(email);
        return "Entretien pris et notification envoyée à " + email;
    }

    @PostMapping("/rejet")
    public String rejeterCandidat(@RequestParam String email) {
        talentService.rejetCandidat(email);
        return "Candidat rejeté et notification envoyée à " + email;
    }

    @GetMapping("/all")
    @JsonView( POV.Public.class )
    public List<Talent> getAllTalents() {
        return talentService.getAll();
    }
}
