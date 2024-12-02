package mg.itu.rh.dto.rupture;

import lombok.Data;

@Data
public class DemissionRequest extends RuptureRequest {
    @Override
    public String getSubject() {
        return "Declaration de demission";
    }
}
