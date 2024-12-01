package mg.itu.rh.entity.recrutement;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.entity.critere.Competence;
import mg.itu.rh.entity.id.IdCompetencePoste;
import mg.itu.rh.entity.interne.Poste;
import mg.itu.rh.other.POV;

@Entity
@Data
@Table( name = "competence_poste" )
public class CompetencePoste {
    @EmbeddedId
    private IdCompetencePoste id;

    @ManyToOne( fetch = FetchType.LAZY )
    @JsonView( { POV.Public.class } )
    @JoinColumn( name = "id_competence", insertable = false, updatable = false )
    private Competence competence;

    @ManyToOne( fetch = FetchType.LAZY )
    @JsonView( { POV.Full.class } )
    @JoinColumn( name = "id_poste", insertable = false, updatable = false )
    private Poste poste;

    @JsonView( { POV.Public.class } )
    @Column( name = "point_requis")
    private int point;
}
