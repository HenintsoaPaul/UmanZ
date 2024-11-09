package mg.itu.rh.entity.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class IdCompetenceAnnonce implements Serializable {
    @Column(name = "id_competence")
    private Long idCompetence;
    @Column(name = "id_annonce")
    private Long idAnnonce;
}
