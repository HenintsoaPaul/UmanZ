package mg.itu.rh.entity.question;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.other.POV;

@Entity
@Data
@Table(name = "reponse")
public class Reponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_reponse")
    @JsonView({POV.Public.class})
    private Long idReponse;

    @JsonView({POV.Public.class})
    private String reponse;

    @JsonView({POV.Public.class})
    private double note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_question_technique")
    private QuestionTechnique questionTechnique;
}
