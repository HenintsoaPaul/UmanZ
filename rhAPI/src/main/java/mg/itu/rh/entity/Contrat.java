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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_contrat")
    private Long idContrat;

    private String contrat;

    @Column(name="date_debut")
    private LocalDate dateDebut;
    
    @Column(name="date_fin")
    private LocalDate dateFin;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_talent")
    private Talent talent;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_type_contrat")
    private TypeContrat typeContrat;

    @Column(name = "nb_jour_conge_an")
    private int nbJourCongeAn;

    @Column(name = "salaire_horaire")
    private double salaireHoraire;

    @Column(name = "nb_jour_semaine")
    private int nbJourSemaine;

    @Column(name = "nb_heure_jour")
    private double nbHeureJour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_poste")
    private Poste poste;

    public Contrat(ContratDTO contratDTO){
        this.setContrat(contratDTO.getContrat());
        this.setNbHeureJour(contratDTO.getNbHeureJour());
        this.setNbJourSemaine(contratDTO.getNbJourSemaine());
        this.setSalaireHoraire(contratDTO.getSalaireHoraire());
        this.setDateFin(contratDTO.getDateFin());
        this.setDateDebut(contratDTO.getDateDebut());
        this.setNbJourCongeAn(contratDTO.getNbJourCongeAn());
    }

    public Contrat(){

    }
}
