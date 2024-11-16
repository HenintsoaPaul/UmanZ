package mg.itu.rh.entity.question;

import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.entity.Talent;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "resultat_technique")
public class ResultatTechnique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resultat_technique")
    private Long idResultatTechnique;

    private double note;

    @Column(name = "date_resultat")
    private LocalDate dateResultat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_talent")
    private Talent talent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_domaine")
    private Domaine domaine;
}
