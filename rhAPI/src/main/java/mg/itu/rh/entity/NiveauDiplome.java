package mg.itu.rh.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class NiveauDiplome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_niveau_diplome")
    private Long idNiveauDiplome;

    @Column(name = "niveau_diplome")
    private int niveau;

    @Column(name = "nom_niveau_diplome")
    private String niveauDiplome;
}
