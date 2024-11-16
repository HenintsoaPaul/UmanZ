package mg.itu.rh.entity.question;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.entity.Talent;
import mg.itu.rh.entity.question.id.IdQuestionTalent;
import mg.itu.rh.other.POV;

import java.util.List;

@Entity
@Data
@Table(name = "question_talent")
public class QuestionTalent {
    @EmbeddedId
    private IdQuestionTalent id;

    @JsonView({POV.Public.class,POV.Question.class})
    private Double note;

    @JsonView({POV.Public.class,POV.Question.class})
    private String reponse;

    @MapsId("idEvaluation")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evaluation")
    private Evaluation evaluation;

    @MapsId("idTalent")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_talent")
    @JsonView({POV.Public.class,POV.Question.class})
    private Talent talent;

    @MapsId("idQuestionProjet")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_question_projet")
    @JsonView({POV.Public.class,POV.Question.class})
    private QuestionProjet questionProjet;
}
