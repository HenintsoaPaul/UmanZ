package mg.itu.rh.entity.interne;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.other.POV;

@Entity
@Data
@Table(name = "type_poste")
public class TypePoste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_poste")
    @JsonView( { POV.Public.class, POV.Emp.class } )
    private Long idTypePoste;

    @Column(name = "nom_type_poste")
    @JsonView( { POV.Public.class, POV.Emp.class } )
    String typePoste;

    @Column(name = "rang_type_poste")
    int rang;
}
