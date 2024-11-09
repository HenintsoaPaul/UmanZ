package mg.itu.rh.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.other.POV;

import java.time.LocalDate;

@Entity
@Data
public class Conge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conge")
    @JsonView({POV.Public.class})
    private Long idConge;

    @Column(name = "date_debut")
    @JsonView({POV.Public.class})
    private LocalDate dateDebut;

    @Column(name = "nb_jour")
    @JsonView({POV.Public.class})
    private int nbJour;

    @JsonView({POV.Public.class})
    private String motif;

    @Column(name = "date_validation")
    private LocalDate dateValidation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonView({POV.Public.class})
    @JoinColumn(name = "id_contrat")
    private Contrat contrat;

    @JsonView({POV.Public.class})
    @Transient
    private LocalDate dateFin;

    public Conge(){

    }

    public Conge(Long idConge,LocalDate dateDebut,int nbJour,String motif){
        this.setIdConge(idConge);
        this.setDateDebut(dateDebut);
        this.setNbJour(nbJour);
        this.setMotif(motif);
        this.setDateFin();
    }

    protected void setDateFin(){
        this.setDateFin(dateDebut.plusDays(nbJour));
    }
}
