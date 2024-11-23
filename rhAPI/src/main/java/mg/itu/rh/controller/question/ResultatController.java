package mg.itu.rh.controller.question;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.rh.dto.ResultatDTO;
import mg.itu.rh.entity.question.Resultat;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.question.ResultatService;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/resultat")
public class ResultatController {
    private final ResultatService resultatService;

    public ResultatController(ResultatService resultatTechniqueService) {
        this.resultatService = resultatTechniqueService;
    }

    @GetMapping("/talent/{id}")
    @JsonView(POV.Question.class)
    public List<Resultat> findAllByIdTalent(@PathVariable("id")Long idTalent){
        return resultatService.findResultatTechniqueByIdTalent(idTalent);
    }
    /*
    {
        "idTalent":4,
            "questionReponses":[
        {
            "idQuestion":1,
                "idReponse":17
        },
        {
            "idQuestion":2,
                "idReponse":20
        },
        {
            "idQuestion":3,
                "idReponse":24
        },
        {
            "idQuestion":4,
                "idReponse":26
        }
    ]
    }
    */
    @PostMapping
    @JsonView(POV.Question.class)
    public Resultat findAllByIdTalent(@RequestBody ResultatDTO resultat){
        return resultatService.save(resultat);
    }
}
