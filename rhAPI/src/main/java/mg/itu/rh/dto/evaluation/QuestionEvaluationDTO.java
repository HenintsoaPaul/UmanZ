package mg.itu.rh.dto.evaluation;

import lombok.Data;

@Data
public class QuestionEvaluationDTO {
    private Long idQuestionEvaluation;
    private Double note;
    private String reponse;
    private Long idQuestionProjet;
}
