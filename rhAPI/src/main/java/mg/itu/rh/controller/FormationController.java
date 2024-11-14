package mg.itu.rh.controller;

import mg.itu.rh.entity.Formation;
import mg.itu.rh.repository.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/formations" )
public class FormationController {
    private final FormationRepository formationRepository;

    public FormationController( FormationRepository formationRepository ) {
        this.formationRepository = formationRepository;
    }

    @PostMapping
    public Formation create( @RequestBody Formation formation ) {
        System.out.println( formation.toString() );
        return formationRepository.save( formation );
    }

    @GetMapping
    public List<Formation> getAll() {
        return formationRepository.findAll();
    }
}
