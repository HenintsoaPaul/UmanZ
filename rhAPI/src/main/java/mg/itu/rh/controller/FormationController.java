package mg.itu.rh.controller;

import mg.itu.rh.entity.Formation;
import mg.itu.rh.repository.FormationRepository;
import mg.itu.rh.service.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/formations" )
public class FormationController {
    private final FormationRepository formationRepository;
    private final FormationService formationService;

    public FormationController( FormationRepository formationRepository, FormationService formationService ) {
        this.formationRepository = formationRepository;
        this.formationService = formationService;
    }

    @PostMapping
    public Formation create( @RequestBody Formation formation ) {
        return formationRepository.save( formation );
    }

    @GetMapping
    public List<Formation> getAll() {
        return formationRepository.findAll();
    }

    @GetMapping( "/disponible" )
    public List<Formation> getAllDisponible() {
        return formationService.findAllDisponible();
    }
}
