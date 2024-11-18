package mg.itu.rh.entity.question.id;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@Data
@EqualsAndHashCode
public class IdQuestionEvaluation implements Serializable {
    private Long idQuestionProjet;
    private Long idEvaluation;
}
