package mg.itu.rh.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import mg.itu.rh.entity.Diplome;
import mg.itu.rh.entity.NiveauDiplome;
import mg.itu.rh.other.POV;

@Data
public class DiplomeDTO {
    @JsonView( { POV.Public.class } )
    private Diplome diplome;
    @JsonView( { POV.Public.class } )
    private NiveauDiplome niveauDiplome;
}
