package mg.itu.rh.entity;

import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.entity.id.IdTalentCompetence;

@Entity
@Data
@Table(name = "talent_competence")
public class TalentCompetence {
    @EmbeddedId
    private IdTalentCompetence idTalentCompetence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_talent",insertable = false,updatable = false)
    private Talent talent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_competence",insertable = false,updatable = false)
    private Competence competence;

    private int point;
}
