package mg.itu.rh.controller;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.rh.entity.ExperiencePoste;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.ExperiencePosteService;
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
