package mg.itu.rh.controller.evaluation;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.rh.entity.evaluation.Domaine;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.evaluation.DomaineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/domaine")
public class DomaineController {
    private final DomaineService domaineService;

    public DomaineController(DomaineService domaineService) {
        this.domaineService = domaineService;
    }

    @GetMapping
    @JsonView({POV.Public.class})
    public List<Domaine> findAll(){
        return domaineService.findAll();
    }
}
