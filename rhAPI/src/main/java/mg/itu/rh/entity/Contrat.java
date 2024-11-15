package mg.itu.rh.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import mg.itu.rh.dto.ContratDTO;
import mg.itu.rh.other.POV;

@Entity
@Data
public class Contrat {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id_contrat" )
    @JsonView( POV.Public.class )
    private Long idContrat;

    @JsonView( POV.Public.class )
    private String contrat;

    @Column( name = "date_debut" )
    @JsonView( POV.Public.class )
    private LocalDate dateDebut;

    @Column( name = "date_fin" )
    @JsonView( POV.Public.class )
    private LocalDate dateFin;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "id_talent" )
    @JsonView( POV.Public.class )
    private Talent talent;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "id_type_contrat" )
    @JsonView( POV.Public.class )
    private TypeContrat typeContrat;

    @Column( name = "nb_jour_conge_an" )
    @JsonView( POV.Public.class )
    private int nbJourCongeAn;

    @Column( name = "salaire_horaire" )
    @JsonView( POV.Public.class )
    private double salaireHoraire;

    @Column( name = "nb_jour_semaine" )
    @JsonView( POV.Public.class )
    private int nbJourSemaine;

    @Column( name = "nb_heure_jour" )
    @JsonView( POV.Public.class )
    private double nbHeureJour;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "id_poste" )
    @JsonView( POV.Public.class )
    private Poste poste;

    public Contrat( ContratDTO contratDTO ) {
        this.setContrat( contratDTO.getContrat() );
        this.setNbHeureJour( contratDTO.getNbHeureJour() );
        this.setNbJourSemaine( contratDTO.getNbJourSemaine() );
        this.setSalaireHoraire( contratDTO.getSalaireHoraire() );
        this.setNbJourCongeAn( contratDTO.getNbJourCongeAn() );
        this.setDateDebut( contratDTO.getDateDebut() );
        LocalDate dFin = contratDTO.getDateFin();
        if ( dFin != null ) this.setDateFin( contratDTO.getDateFin() );
    }

    public Contrat() {

    }
}
