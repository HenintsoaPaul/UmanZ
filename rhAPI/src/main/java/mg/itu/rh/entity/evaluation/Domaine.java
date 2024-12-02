package mg.itu.rh.entity.evaluation;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.other.POV;

@Entity
@Data
public class Domaine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_domaine")
    @JsonView({POV.Public.class,POV.Question.class})
    private Long idDomaine;

    @JsonView({POV.Public.class,POV.Question.class})
    private String domaine;
}
