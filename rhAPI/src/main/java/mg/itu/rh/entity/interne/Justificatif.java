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
public class Justificatif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_justficatif")
    @JsonView({POV.Public.class})
    private Long idJustificatif;

    @Column(name = "date_justificatif")
    @JsonView({POV.Public.class})
    private LocalDate dateJustificatif;

    @Column(name = "image_justificatif")
    @JsonView({POV.Public.class})
    private String imageJustificatif;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonView({POV.Public.class})
    @JoinColumn(name = "id_type_justficatif")
    private TypeJustificatif typeJustificatif;
}
