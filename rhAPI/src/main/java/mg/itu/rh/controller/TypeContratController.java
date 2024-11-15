package mg.itu.rh.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.rh.other.POV;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mg.itu.rh.entity.TypeContrat;
import mg.itu.rh.repository.TypeContratRepository;


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
