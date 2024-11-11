package mg.itu.rh.controller;

import mg.itu.rh.entity.Formation;
import mg.itu.rh.repository.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/formations")
public class FormationController {

    @Autowired
    private FormationRepository formationRepository;

    /**
     *
     * @param formation {
     *      "nomFormation": "Power up",
     *      "dateDebut": "2024-11-11",
     *      "dateFin": "2024-11-13"
     * }
     * @return la formation créée
     */
    @PostMapping
    public Formation create(Formation formation) {
        return formationRepository.save(formation);
    }

}
