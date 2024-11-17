package mg.itu.rh.entity;

import mg.itu.rh.id.*;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@IdClass(TalentCompetenceId.class)
public class TalentCompetence {
    @Id
    @Column(name = "id_talent", insertable = false, updatable = false)
    private Long idTalent;

    @Id
    @Column(name = "id_competence", insertable = false, updatable = false)
    private Long idCompetence;

    @ManyToOne
    @JoinColumn(name = "id_talent", referencedColumnName = "id_talent")
    private Talent talent;

    @ManyToOne
    @JoinColumn(name = "id_competence", referencedColumnName = "id_competence")
    private Competence competence;

    @Column(name = "point", nullable = false)
    private int point;

    public int getNiveau() {
        return this.point;
    }

    public void setPoint(int point) {
        if (point < 0) {
            throw new IllegalArgumentException("Le point ne peut pas être négatif.");
        }
        this.point = point;
    }
}
