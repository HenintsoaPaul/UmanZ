package mg.itu.rh.dto.critere;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import mg.itu.rh.entity.critere.Diplome;
import mg.itu.rh.entity.critere.NiveauDiplome;
import mg.itu.rh.other.POV;

@Data
public class DiplomeDTO {
    @JsonView( { POV.Public.class } )
    private Diplome diplome;
    @JsonView( { POV.Public.class } )
    private NiveauDiplome niveauDiplome;
}
