package mg.itu.rh.entity.id;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class IdAnnonceLangue implements Serializable {
    private Long idAnnonce;
    private Long idLangue;
    private Long idNiveauLangue;
}
