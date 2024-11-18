package mg.itu.rh.entity.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class IdTalentLangue implements Serializable {
    @Column(name = "id_langue")
    private Long idLangue;
    @Column(name = "id_niveau_langue")
    private Long idNiveauLangue;
    @Column(name = "id_talent")
    private Long idTalent;
}
