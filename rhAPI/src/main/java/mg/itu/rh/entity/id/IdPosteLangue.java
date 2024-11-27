package mg.itu.rh.entity.id;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdPosteLangue implements Serializable {
    private Long idPoste;
    private Long idLangue;
    private Long idNiveauLangue;
}
