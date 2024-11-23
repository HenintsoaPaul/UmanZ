package mg.itu.rh.controller.critere;

import mg.itu.rh.entity.critere.Competence;
import mg.itu.rh.service.critere.CompetenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/competences")
public class CompetenceController {

    @Autowired
    private CompetenceService competenceService;

    @GetMapping
    public List<Competence> getAllCompetences() {
        return competenceService.findAll();
    }

}
