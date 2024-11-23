package mg.itu.rh.controller.question;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.rh.entity.question.Question;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.question.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionTechniqueService;

    public QuestionController(QuestionService questionTechniqueService) {
        this.questionTechniqueService = questionTechniqueService;
    }

    @GetMapping("/{id}")
    @JsonView(POV.Public.class)
    public List<Question> findAllRandomlyByIdDomaine(@PathVariable("id")Long idDomaine){
        return questionTechniqueService.findAllRandomlyByIdDomaine(idDomaine);
    }
}
