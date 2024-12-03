package mg.itu.rh.dto.rupture;

import lombok.Data;

@Data
public class RenvoiRequest extends RuptureRequest {
    @Override
    public String getSubject() {
        return "Annonce de licenciement";
    }
}

