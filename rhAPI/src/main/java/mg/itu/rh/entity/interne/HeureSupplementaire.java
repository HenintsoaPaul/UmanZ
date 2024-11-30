package mg.itu.rh.entity.interne;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class HeureSupplementaire {
    @Id
    private long id;

    private String motif;

    @Column(nullable = false)
    private LocalDateTime dateHeure;

    @Column(nullable = false)
    private LocalDateTime dateHeureCreation;

    private double nbHeure;

    private double tauxMajoration;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_contrat")
    private Contrat contrat;
}
