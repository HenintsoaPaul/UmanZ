package mg.itu.rh.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Poste {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_poste")
    private Long idPoste;

    @Column(name="description_poste")
    private String description;
}
