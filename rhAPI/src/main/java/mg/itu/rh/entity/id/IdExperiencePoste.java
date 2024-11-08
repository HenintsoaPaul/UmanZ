package mg.itu.rh.entity.id;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class IdExperiencePoste {
    private Long idExperience;
    private Long idPoste;
}
