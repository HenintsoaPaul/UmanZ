package mg.itu.rh.entity.id;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdAnnonceLangue implements Serializable {
    private Long idAnnonce;
    private Long idLangue;
    private Long idNiveauLangue;
}
