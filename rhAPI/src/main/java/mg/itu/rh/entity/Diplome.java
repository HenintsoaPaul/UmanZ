package mg.itu.rh.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.other.POV;

@Data
@Entity
public class Diplome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({POV.Public.class})
    @Column(name="id_diplome")
    private Long idDiplome;

    @JsonView({POV.Public.class})
    @Column(name = "nom_diplome")
    private String diplome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_niveau_diplome")
    @JsonView({POV.Public.class})
    private NiveauDiplome niveauDiplome;
}
