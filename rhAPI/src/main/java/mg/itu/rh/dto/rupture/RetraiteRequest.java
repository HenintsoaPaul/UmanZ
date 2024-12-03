package mg.itu.rh.dto.rupture;

import lombok.Data;

@Data
public class RetraiteRequest extends RuptureRequest {
    @Override
    public String getSubject() {
        return "Declaration de depart a la retraite";
    }
}
