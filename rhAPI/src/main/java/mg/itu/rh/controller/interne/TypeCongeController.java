package mg.itu.rh.controller.interne;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.rh.entity.interne.TypeConge;
import mg.itu.rh.other.POV;
import mg.itu.rh.repository.interne.TypeCongeRepository;
import mg.itu.rh.service.interne.TypeCongeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping( "/type_conges" )
public class TypeCongeController {
    private final TypeCongeService typeCongeService;
    private final TypeCongeRepository typeCongeRepository;

    public TypeCongeController( TypeCongeService typeCongeService, TypeCongeRepository typeCongeRepository ) {
        this.typeCongeService = typeCongeService;
        this.typeCongeRepository = typeCongeRepository;
    }

    @GetMapping
    @JsonView( POV.Public.class )
    public List<TypeConge> getTypeConge() {
        return typeCongeRepository.findAll();
    }
}
