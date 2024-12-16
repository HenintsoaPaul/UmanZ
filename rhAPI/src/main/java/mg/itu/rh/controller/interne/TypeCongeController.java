package mg.itu.rh.controller.interne;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import mg.itu.rh.entity.interne.TypeConge;
import mg.itu.rh.other.POV;
import mg.itu.rh.repository.interne.TypeCongeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping( "/type_conges" )
@RequiredArgsConstructor
public class TypeCongeController {
    private final TypeCongeRepository typeCongeRepository;

    @GetMapping
    @JsonView( POV.Public.class )
    public List<TypeConge> getTypeConge() {
        return typeCongeRepository.findAll();
    }
}
