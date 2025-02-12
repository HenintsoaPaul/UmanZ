package mg.itu.rh.entity.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class IdTalentCompetence implements Serializable {
    @Column(name = "id_talent")
    private Long idTalent;

    @Column(name = "id_competence")
    private Long idCompetence;
}
