package mg.itu.rh.entity.interne;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.other.POV;

@Data
@Entity( name = "type_rupture" )
public class TypeRupture {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id_type_rupture" )
    @JsonView( POV.Public.class )
    private Long idTypeRupture;

    @Column( name = "nom_type_rupture" )
    @JsonView( POV.Public.class )
    private String typeRupture;
}
