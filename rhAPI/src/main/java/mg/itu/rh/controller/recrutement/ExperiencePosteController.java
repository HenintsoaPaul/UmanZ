package mg.itu.rh.controller.recrutement;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.rh.entity.recrutement.ExperiencePoste;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.recrutement.ExperiencePosteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/experience-poste")
public class ExperiencePosteController {

    @Autowired
    private ExperiencePosteService experiencePosteService;

    @GetMapping("/experience")
    @JsonView( POV.Full.class)
    public List<ExperiencePoste> getAllExperiencePostes() {
        return experiencePosteService.findAll();
    }

}
