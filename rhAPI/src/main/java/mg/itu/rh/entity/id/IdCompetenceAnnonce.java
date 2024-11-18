package mg.itu.rh.entity.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Data
@NoArgsConstructor
public class IdCompetenceAnnonce implements Serializable {
    @Column(name = "id_competence")
    private Long idCompetence;
    @Column(name = "id_annonce")
    private Long idAnnonce;

    public IdCompetenceAnnonce(Long idCompetence, Long idAnnonce) {
        this.idCompetence = idCompetence;
        this.idAnnonce = idAnnonce;
    }
}
