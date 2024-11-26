package mg.itu.rh.entity.interne;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.other.POV;

@Entity
@Data
@Table( name = "type_justificatif" )
public class TypeJustificatif {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id_type_justificatif" )
    @JsonView( POV.Public.class )
    private Long idTypeJustificatif;

    @Column( name = "nom_type_justificatif" )
    @JsonView( POV.Public.class )
    private String nomTypeJustificatif;
}
