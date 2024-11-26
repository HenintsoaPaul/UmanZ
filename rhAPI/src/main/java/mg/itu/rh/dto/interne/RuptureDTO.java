package mg.itu.rh.dto.interne;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import mg.itu.rh.other.POV;

import java.time.LocalDate;

@Data
public class RuptureDTO {
    @JsonView(POV.Public.class)
    private LocalDate dateRupture;
    @JsonView(POV.Public.class)
    private String motif;
    @JsonView(POV.Public.class)
    private Long idContrat;
    @JsonView(POV.Public.class)
    private Long idTypeRupture;
}
