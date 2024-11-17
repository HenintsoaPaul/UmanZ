package mg.itu.rh.entity.question;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.entity.question.id.IdQuestionEvaluation;
import mg.itu.rh.other.POV;

import java.util.List;

@Entity
@Data
@Table(name = "question_evaluation")
public class QuestionEvaluation {
    @EmbeddedId
    private IdQuestionEvaluation id=new IdQuestionEvaluation();

    @MapsId("idQuestionProjet")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonView({POV.Public.class,POV.Question.class})
    @JoinColumn(name = "id_question_projet",insertable = false,updatable = false)
    private QuestionProjet questionProjet;

    @MapsId("idEvaluation")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evaluation",insertable = false,updatable = false)
    private Evaluation evaluation;

    @JsonView({POV.Public.class,POV.Question.class})
    private Double note;

    @JsonView({POV.Public.class,POV.Question.class})
    private String reponse;
}
