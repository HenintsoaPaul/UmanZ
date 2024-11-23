package mg.itu.rh.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "type_poste")
public class TypePoste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_poste")
    private Long idTypePoste;

    @Column(name = "nom_type_poste")
    String typePoste;

    @Column(name = "rang_type_poste")
    int rangTypePoste;
}
