package mg.itu.rh.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import mg.itu.rh.other.POV;

@Data
public class AnnonceLangueDTO {
    @JsonView({POV.Public.class})
    private Long idLangue;
    @JsonView({POV.Public.class})
    private Long idNiveauLangue;
}
