package mg.itu.rh.entity;

import mg.itu.rh.id.*;
import mg.itu.rh.other.POV;


import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TalentCompetence {
    @EmbeddedId
    private TalentCompetenceId talentCompetenceId;

    @ManyToOne
    @MapsId("idTalent")
    @JoinColumn(name = "id_talent", insertable = false,updatable = false)
    // @JsonView( POV.Public.class )
    private Talent talent;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idCompetence")
    @JoinColumn(name = "id_competence", insertable = false,updatable = false)
    // @JsonView( POV.Public.class )
    private Competence competence;

    @Column(name = "point", nullable = false)
    // @JsonView( POV.Public.class )
    private int point;

    public int getNiveau() {
        return this.point;
    }

    public void setPoint(int point) {
        // if (point < 0) {
        //     throw new IllegalArgumentException("Le point ne peut pas être négatif.");
        // }
        this.point = point;
    }
}
