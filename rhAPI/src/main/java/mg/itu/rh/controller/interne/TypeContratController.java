package mg.itu.rh.controller.interne;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.rh.other.POV;
import mg.itu.rh.repository.interne.TypeContratRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mg.itu.rh.entity.interne.TypeContrat;


@RestController
@RequestMapping( "/type_contrat" )
public class TypeContratController {
    private final TypeContratRepository typeContratRepository;

    public TypeContratController( TypeContratRepository typeContratRepository ) {
        this.typeContratRepository = typeContratRepository;
    }

    @GetMapping
    @JsonView( POV.Public.class )
    public List<TypeContrat> getTypeContrat() {
        return typeContratRepository.findAll();
    }
}
