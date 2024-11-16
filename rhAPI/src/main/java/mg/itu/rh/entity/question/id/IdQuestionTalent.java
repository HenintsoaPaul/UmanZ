package mg.itu.rh.entity.question.id;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@Data
@EqualsAndHashCode
public class IdQuestionTalent implements Serializable {
    private Long idQuestionProjet;
    private Long idTalent;
    private Long idEvaluation;
}
