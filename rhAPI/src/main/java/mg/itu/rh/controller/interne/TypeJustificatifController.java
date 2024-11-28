package mg.itu.rh.controller.interne;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.rh.entity.interne.TypeJustificatif;
import mg.itu.rh.other.POV;
import mg.itu.rh.repository.interne.TypeJustificatifRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping( "/type_justificatifs" )
public class TypeJustificatifController {
    private final TypeJustificatifRepository typeJustificatifRepository;

    public TypeJustificatifController( TypeJustificatifRepository typeJustificatifRepository ) {
        this.typeJustificatifRepository = typeJustificatifRepository;
    }

    @GetMapping
    @JsonView( POV.Public.class )
    public List<TypeJustificatif> getTypeJustificatif() {
        return typeJustificatifRepository.findAll();
    }
}
