package mg.itu.rh.dto.rupture;

import lombok.Data;

@Data
public class RenvoiRequest extends RuptureRequest {
    private String email;

    @Override
    public String getSubject() {
        return "Annonce de licenciement";
    }
}

