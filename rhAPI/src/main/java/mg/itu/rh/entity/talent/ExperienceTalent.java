package mg.itu.rh.entity.talent;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.entity.Poste;
import mg.itu.rh.entity.id.IdExperienceTalent;
import mg.itu.rh.other.POV;

@Entity
@Data
@Table( name = "experience_talent" )
public class ExperienceTalent {
    @EmbeddedId
    private IdExperienceTalent id;

    @JsonView( { POV.Full.class } )
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "id_talent", insertable = false, updatable = false )
    private Talent talent;

    @JsonView( { POV.Public.class } )
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "id_poste", updatable = false, insertable = false )
    private Poste poste;

    @JsonView( { POV.Public.class } )
    private int ans;
}
