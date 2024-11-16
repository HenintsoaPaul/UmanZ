package mg.itu.rh.entity.question;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.other.POV;

@Entity
@Data
@Table(name = "question_projet")
public class QuestionProjet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_question_projet")
    @JsonView({POV.Public.class})
    private Long idQuestionProjet;

    @JsonView({POV.Public.class})
    private String question;
}