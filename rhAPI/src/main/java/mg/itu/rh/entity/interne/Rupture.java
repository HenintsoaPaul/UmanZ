package mg.itu.rh.entity.interne;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.dto.interne.RuptureDTO;
import mg.itu.rh.other.POV;

import java.time.LocalDate;

@Entity
@Data
public class Rupture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rupture")
    @JsonView({POV.Public.class})
    private Long idRupture;

    @Column(name = "date_rupture")
    @JsonView({POV.Public.class})
    private LocalDate dateRupture;

    @JsonView({POV.Public.class})
    private String motif;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contrat")
    @JsonView({POV.Public.class})
    private Contrat contrat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonView({POV.Public.class})
    @JoinColumn(name = "id_type_rupture")
    private TypeRupture typeRupture;

    public Rupture(){

    }

    public Rupture(RuptureDTO ruptureDTO){
        this.setDateRupture(ruptureDTO.getDateRupture());
        this.setMotif(ruptureDTO.getMotif());
    }
}
