package mg.itu.rh.entity.interne;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.entity.critere.Competence;
import mg.itu.rh.entity.id.IdCompetenceCible;
import mg.itu.rh.entity.interne.Formation;
import mg.itu.rh.other.POV;

@Entity
@Data
@Table( name = "competence_cible" )
public class CompetenceCible {
    @EmbeddedId
    @JsonView( POV.Public.class )
    private IdCompetenceCible id;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "id_competence", updatable = false, insertable = false )
    @JsonView( POV.Public.class )
    private Competence competence;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "id_formation", insertable = false, updatable = false )
    @JsonView( POV.Full.class )
    private Formation formation;

    @Column( name = "point_gagne" )
    @JsonView( POV.Public.class )
    private int pointGagne;
}
