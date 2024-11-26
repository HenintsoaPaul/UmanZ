package mg.itu.rh.entity.critere;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.other.POV;

@Entity
@Data
public class Langue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_langue")
    @JsonView({POV.Public.class})
    private Long idLangue;

    @JsonView({POV.Public.class})
    private String langue;
}
