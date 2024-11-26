package mg.itu.rh.entity.recrutement;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.entity.Poste;
import mg.itu.rh.entity.critere.Diplome;
import mg.itu.rh.entity.id.IdPosteDiplome;
import mg.itu.rh.other.POV;

@Entity
@Data
@Table( name = "diplome_poste" )
public class PosteDiplome {
    @EmbeddedId
    private IdPosteDiplome id;

    @ManyToOne( fetch = FetchType.LAZY )
    @MapsId( "idPoste" )
    @JoinColumn( name = "id_poste", insertable = false, updatable = false )
    private Poste poste;

    @ManyToOne( fetch = FetchType.LAZY )
    @MapsId( "idDiplome" )
    @JoinColumn( name = "id_diplome", insertable = false, updatable = false )
    @JsonView( { POV.Public.class } )
    private Diplome diplome;
}
