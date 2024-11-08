package mg.itu.rh.entity.id;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class IdExperienceTalent implements Serializable {
    private Long idAnnonce;
    private Long idPoste;
}
