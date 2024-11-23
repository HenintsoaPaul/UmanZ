package mg.itu.rh.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import mg.itu.rh.entity.Langue;
import mg.itu.rh.entity.NiveauLangue;
import mg.itu.rh.other.POV;

@Data
public class LangueDTO {
    @JsonView( { POV.Public.class } )
    private Langue langue;
    @JsonView( { POV.Public.class } )
    private NiveauLangue niveauLangue;
}
