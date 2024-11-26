package mg.itu.rh.entity.critere;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.other.POV;

@Entity
@Data
@Table(name = "niveau_langue")
public class NiveauLangue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_niveau_langue")
    @JsonView({POV.Public.class})
    private Long idNiveauLangue;

    @Column(name = "nom_niveau_langue")
    @JsonView({POV.Public.class})
    private String niveauLangue;

    @Column(name = "niveau_langue")
    @JsonView({POV.Public.class})
    private int niveau;
}
