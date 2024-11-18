package mg.itu.rh.controller.question;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.rh.dto.ResultatTechniqueDTO;
import mg.itu.rh.entity.question.ResultatTechnique;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.question.ResultatTechniqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/resultat_technique")
public class ResultatTechniqueController {
    private final ResultatTechniqueService resultatTechniqueService;

    public ResultatTechniqueController(ResultatTechniqueService resultatTechniqueService) {
        this.resultatTechniqueService = resultatTechniqueService;
    }

    @GetMapping("/talent/{id}")
    public List<ResultatTechnique> findAllByIdTalent(@PathVariable("id")Long idTalent){
        return resultatTechniqueService.findResultatTechniqueByIdTalent(idTalent);
    }
    /*
    {
        "idTalent":4,
            "questionReponses":[
        {
            "idQuestionTechnique":1,
                "idReponse":17
        },
        {
            "idQuestionTechnique":2,
                "idReponse":20
        },
        {
            "idQuestionTechnique":3,
                "idReponse":24
        },
        {
            "idQuestionTechnique":4,
                "idReponse":26
        }
    ]
    }
    */
    @PostMapping
    @JsonView(POV.Question.class)
    public ResultatTechnique findAllByIdTalent(@RequestBody ResultatTechniqueDTO resultat){
        return resultatTechniqueService.save(resultat);
    }
}
