package mg.itu.rh.controller.question;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.rh.entity.question.QuestionTechnique;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.question.QuestionTechniqueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/question_technique")
public class QuestionTechniqueController {
    private final QuestionTechniqueService questionTechniqueService;

    public QuestionTechniqueController(QuestionTechniqueService questionTechniqueService) {
        this.questionTechniqueService = questionTechniqueService;
    }

    @GetMapping("/{id}")
    @JsonView(POV.Public.class)
    public List<QuestionTechnique> findAllRandomlyByIdDomaine(@PathVariable("id")Long idDomaine){
        return questionTechniqueService.findAllRandomlyByIdDomaine(idDomaine);
    }
}
