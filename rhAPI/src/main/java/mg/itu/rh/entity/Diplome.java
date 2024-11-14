package mg.itu.rh.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Diplome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_diplome")
    private Long idDiplome;

    @Column(name = "nom_diplome")
    private String diplome;
}
