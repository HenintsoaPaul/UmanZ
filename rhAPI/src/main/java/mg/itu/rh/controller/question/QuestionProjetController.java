package mg.itu.rh.controller.question;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.rh.entity.question.QuestionProjet;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.question.QuestionProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/question_projet")
public class QuestionProjetController {
    private final QuestionProjetService questionProjetService;

    public QuestionProjetController(QuestionProjetService questionProjetService) {
        this.questionProjetService = questionProjetService;
    }

    @GetMapping
    @JsonView(POV.Public.class)
    public List<QuestionProjet> findAllRandomly(){
        return questionProjetService.findAllRandomly();
    }
}
