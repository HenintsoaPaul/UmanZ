package mg.itu.rh.controller;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.rh.entity.Conge;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.CongeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/conge")
public class CongeController {

    @Autowired
    private CongeService congeService;

    @GetMapping("/talent/{id}")
    @JsonView(POV.Public.class)
    public List<Conge> getCongeByTalent(@PathVariable("id") Long id){
        return congeService.findCongeByIdTalent(id);
    }

}
