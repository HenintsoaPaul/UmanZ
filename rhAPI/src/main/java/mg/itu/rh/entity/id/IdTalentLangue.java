package mg.itu.rh.entity.id;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class IdTalentLangue implements Serializable {
    private Long idLangue;
    private Long idNiveauLangue;
    private Long idTalent;
}
