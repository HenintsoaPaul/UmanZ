package mg.itu.rh.entity.talent;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.entity.critere.Competence;
import mg.itu.rh.entity.id.IdCompetenceTalent;
import mg.itu.rh.other.POV;

@Entity
@Data
@Table( name = "talent_competence" )
public class CompetenceTalent {
    @EmbeddedId
    private IdCompetenceTalent id;

    @ManyToOne( fetch = FetchType.LAZY )
    @JsonView( { POV.Public.class } )
    @JoinColumn( name = "id_competence", insertable = false, updatable = false )
    private Competence competence;

    @ManyToOne( fetch = FetchType.LAZY )
    @JsonView( { POV.Full.class } )
    @JoinColumn( name = "id_talent", insertable = false, updatable = false )
    private Talent talent;

    @JsonView( { POV.Public.class } )
    private int point;
}
