package mg.itu.rh.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "niveau_langue")
public class NiveauLangue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_niveau_langue")
    private Long idNiveauLangue;

    @Column(name = "nom_niveau_langue")
    private String niveauLangue;

    @Column(name = "niveau_langue")
    private int niveau;
}
