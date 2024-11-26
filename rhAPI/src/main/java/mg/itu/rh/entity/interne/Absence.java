package mg.itu.rh.entity.interne;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.other.POV;

import java.time.LocalDate;

@Entity
@Data
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_absence")
    @JsonView({POV.Public.class})
    private Long idAbsence;

    @Column(name = "motif")
    @JsonView({POV.Public.class})
    private String motif;

    @Column(name = "date_absence", nullable = false)
    @JsonView({POV.Public.class})
    private LocalDate dateAbsence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contrat", nullable = false)
    @JsonView({POV.Public.class})
    private Contrat contrat;

    public Absence() {}

    public Absence(Long idAbsence, String motif, LocalDate dateAbsence, Contrat contrat) {
        this.idAbsence = idAbsence;
        this.motif = motif;
        this.dateAbsence = dateAbsence;
        this.contrat = contrat;
    }
}
