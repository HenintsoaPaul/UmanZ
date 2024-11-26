package mg.itu.rh.entity.talent;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.entity.critere.Diplome;
import mg.itu.rh.entity.id.IdTalentDiplome;
import mg.itu.rh.other.POV;

@Entity
@Data
@Table( name = "talent_diplome" )
public class TalentDiplome {
    @EmbeddedId
    private IdTalentDiplome id;

    @ManyToOne( fetch = FetchType.LAZY )
    @MapsId( "idTalent" )
    @JoinColumn( name = "id_talent", insertable = false, updatable = false )
    private Talent talent;

    @ManyToOne( fetch = FetchType.LAZY )
    @MapsId( "idDiplome" )
    @JoinColumn( name = "id_diplome", insertable = false, updatable = false )
    @JsonView( { POV.Public.class } )
    private Diplome diplome;
}
