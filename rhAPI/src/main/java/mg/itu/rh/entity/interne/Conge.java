package mg.itu.rh.entity.interne;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.itu.rh.other.POV;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Conge {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id_conge" )
    @JsonView( { POV.Public.class, POV.Conge.class } )
    private Long idConge;

    @Column( name = "date_debut" )
    @JsonView( { POV.Public.class, POV.Conge.class } )
    private LocalDate dateDebut;

    @Column( name = "nb_jour" )
    @JsonView( { POV.Public.class, POV.Conge.class } )
    private int nbJour;

    @JsonView( { POV.Public.class, POV.Conge.class } )
    private String motif;

    @Column( name = "date_validation" )
    @JsonView( { POV.Public.class, POV.Conge.class } )
    private LocalDate dateValidation;

    @Column( name = "motif_refus" )
    @JsonView( { POV.Public.class, POV.Conge.class } )
    private String motifRefus;

    @Column( name = "date_refus" )
    @JsonView( { POV.Public.class, POV.Conge.class } )
    private LocalDate dateRefus;

    @ManyToOne( fetch = FetchType.LAZY )
    @JsonView( { POV.Public.class, POV.Conge.class } )
    @JoinColumn( name = "id_contrat" )
    private Contrat contrat;

    @ManyToOne( fetch = FetchType.LAZY )
    @JsonView( { POV.Public.class, POV.Conge.class } )
    @JoinColumn( name = "id_type_conge" )
    private TypeConge typeConge;

    @JsonView( { POV.Public.class, POV.Conge.class } )
    @Transient
    private LocalDate dateFin;

    public Conge( Long idConge, LocalDate dateDebut, int nbJour, String motif, Contrat contrat ) {
        this.setIdConge( idConge );
        this.setDateDebut( dateDebut );
        this.setNbJour( nbJour );
        this.setMotif( motif );
        this.setContrat( contrat );
        this.setDateFin();
    }

    protected void setDateFin() {
        this.setDateFin( dateDebut.plusDays( nbJour ) );
    }
}
