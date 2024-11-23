package mg.itu.rh.entity.recrutement;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.entity.critere.Diplome;
import mg.itu.rh.entity.id.IdAnnonceDiplome;
import mg.itu.rh.entity.recrutement.Annonce;
import mg.itu.rh.other.POV;

@Entity
@Data
@Table( name = "annonce_diplome" )
public class AnnonceDiplome {
    @EmbeddedId
    private IdAnnonceDiplome id;

    @ManyToOne( fetch = FetchType.LAZY )
    @MapsId( "idAnnonce" )
    @JoinColumn( name = "id_annonce", insertable = false, updatable = false )
    private Annonce annonce;

    @ManyToOne( fetch = FetchType.LAZY )
    @MapsId( "idDiplome" )
    @JoinColumn( name = "id_diplome", insertable = false, updatable = false )
    @JsonView( { POV.Public.class } )
    private Diplome diplome;
}
