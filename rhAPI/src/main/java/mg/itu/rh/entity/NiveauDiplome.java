package mg.itu.rh.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import mg.itu.rh.other.POV;

@Entity
@Data
public class NiveauDiplome {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_niveau_diplome")
    @JsonView({POV.Full.class})
    private Long idNiveauDiplome;

    @Column(name = "niveau_diplome")
    @JsonView({POV.Public.class})
    private int nomPoste;

    @Column(name="nom_niveau_diplome")
    @JsonView({POV.Public.class})
    private String nomDiplome;

}

