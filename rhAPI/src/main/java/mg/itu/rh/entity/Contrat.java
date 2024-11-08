package mg.itu.rh.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

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
}
