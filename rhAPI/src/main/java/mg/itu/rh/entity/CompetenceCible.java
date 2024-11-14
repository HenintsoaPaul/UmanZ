package mg.itu.rh.entity;

import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.entity.id.IdCompetenceCible;

@Entity
@Data
@Table( name = "competence_cible" )
public class CompetenceCible {
    @EmbeddedId
    private IdCompetenceCible id;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "id_competence", updatable = false, insertable = false )
    private Competence competence;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "id_formation", insertable = false, updatable = false )
    private Formation formation;

    @Column( name = "point_gagne" )
    private int pointGagne;
}
