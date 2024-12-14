package mg.itu.rh.dto.rupture;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode( callSuper = true )
public class RenvoiRequest extends RuptureRequest {
    @Override
    public String getSubject() {
        return "Annonce de licenciement";
    }
}

