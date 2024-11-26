package mg.itu.rh.entity.critere;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.other.POV;

@Entity
@Data
public class NiveauDiplome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_niveau_diplome")
    @JsonView({ POV.Public.class})
    private Long idNiveauDiplome;

    @Column(name = "niveau_diplome")
    @JsonView({POV.Public.class})
    private int niveau;

    @Column(name = "nom_niveau_diplome")
    @JsonView({POV.Public.class})
    private String niveauDiplome;
}
