package mg.itu.rh.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TypeContrat {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_type_contrat")
    private Long idTypeContrat;

    @Column(name="type_contrat")
    private String typeContrat;
}
