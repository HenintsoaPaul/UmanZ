package mg.itu.rh.controller.question;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.rh.entity.question.Evaluation;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.question.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {
    @Autowired
    private EvaluationService evaluationService;

    @GetMapping("/note/{id}")
    @JsonView(POV.Question.class)
    public List<Evaluation> findEvaluation(@PathVariable("id")Long idTalent){
        return evaluationService.findEvaluationNoted(idTalent);
    }

    @GetMapping("/non_note")
    @JsonView(POV.Question.class)
    public List<Evaluation> findEvaluationNotNoted(){
        return evaluationService.findEvaluationNotNoted();
    }
}
