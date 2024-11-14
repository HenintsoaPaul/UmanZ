package mg.itu.rh.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.itu.rh.entity.id.IdParticipation;
import mg.itu.rh.other.POV;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Participation {
    @EmbeddedId
    private IdParticipation id;

    @ManyToOne( fetch = FetchType.LAZY )
    @JsonView( { POV.Public.class } )
    @JoinColumn( name = "id_contrat", insertable = false, updatable = false )
    private Contrat contrat;

    @ManyToOne( fetch = FetchType.LAZY )
    @JsonView( { POV.Full.class } )
    @JoinColumn( name = "id_formation", insertable = false, updatable = false )
    private Formation formation;

    @Column( name = "date_inscription" )
    @JsonView( { POV.Public.class } )
    private LocalDate dateInscription;
}
