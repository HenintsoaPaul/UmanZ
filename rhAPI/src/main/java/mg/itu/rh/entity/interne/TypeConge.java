package mg.itu.rh.entity.interne;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.other.POV;

@Entity
@Data
@Table( name = "type_conge" )
public class TypeConge {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id_type_conge" )
    @JsonView( {POV.Public.class, POV.Conge.class} )
    private Long idTypeConge;

    @Column( name = "nom_type_conge" )
    @JsonView( {POV.Public.class, POV.Conge.class} )
    private String nomTypeConge;
}
