package mg.itu.rh.dto.rupture;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode( callSuper = true )
public class DemissionRequest extends RuptureRequest {
    @Override
    public String getSubject() {
        return "Declaration de demission";
    }
}
