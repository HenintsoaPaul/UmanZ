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
public class Talent {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_talent")
    @JsonView({POV.Public.class})
    private Long idTalent;

    @JsonView({POV.Public.class})
    private String nom;

    @JsonView({POV.Public.class})
    private String prenom;

    @JsonView({POV.Public.class})
    private String mail;
}
