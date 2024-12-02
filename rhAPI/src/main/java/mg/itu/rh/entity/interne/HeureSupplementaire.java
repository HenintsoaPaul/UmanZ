package mg.itu.rh.entity.interne;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "heure_supplementaire" )
public class HeureSupplementaire {
    @Id
    private long id;

    private String motif;

    @Column( nullable = false )
    private LocalDateTime dateHeureDebut;

    @Column( nullable = false )
    private LocalDateTime dateHeureCreation;

    private double nbHeure;

    private double tauxMajoration;

    @ManyToOne
    @JoinColumn( nullable = false, name = "id_contrat" )
    private Contrat contrat;

    public double montant() {
        return tauxMajoration * contrat.getSalaireHoraire();
    }
}
