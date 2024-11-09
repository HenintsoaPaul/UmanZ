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
public class Poste {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_poste")
    @JsonView({POV.Full.class})
    private Long idPoste;

    @Column(name="description_poste")
    @JsonView({POV.Full.class})
    private String description;
}