package mg.itu.rh.dto.interne;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import mg.itu.rh.entity.interne.Conge;
import mg.itu.rh.other.POV;

@Data
@AllArgsConstructor
public class PendingCongeDTO {
    @JsonView({ POV.Conge.class })
    private Conge conge;
    @JsonView({ POV.Conge.class })
    double solde;
}
