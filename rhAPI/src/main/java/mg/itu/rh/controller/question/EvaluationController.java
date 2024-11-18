package mg.itu.rh.controller.question;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.rh.dto.EvaluationDTO;
import mg.itu.rh.entity.question.Evaluation;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.question.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /*
    {
        "idTalent":1,
            "dateEvaluation":"2024-10-10",
            "evaluations":[
        {
            "reponse":"Je ne sais pas",
                "idQuestionProjet":1
        },
        {
            "reponse":"Je ne sais pas",
                "idQuestionProjet":2
        }
    ]
    }
    */
    @PostMapping
    @JsonView(POV.Question.class)
    public Evaluation save(@RequestBody EvaluationDTO evaluation){
        return evaluationService.save(evaluation);
    }

    /*
    {
        "idEvaluation":8,
        "evaluations":[
            {
                "note":3,
                "idQuestionProjet":1
            },
            {
                "note":7,
                "idQuestionProjet":2
            }
        ]
    }*/
    @PostMapping("/evaluate")
    @JsonView(POV.Question.class)
    public Evaluation evaluation(@RequestBody EvaluationDTO evaluation){
        return evaluationService.update(evaluation);
    }

    @GetMapping("/non_note")
    @JsonView(POV.Question.class)
    public List<Evaluation> findEvaluationNotNoted(){
        return evaluationService.findEvaluationNotNoted();
    }

    @GetMapping("/non_note/{id}")
    @JsonView(POV.Question.class)
    public Evaluation findEvaluationNotNoted(@PathVariable("id")Long id){
        return evaluationService.findEvaluationNotNoted(id);
    }
}
