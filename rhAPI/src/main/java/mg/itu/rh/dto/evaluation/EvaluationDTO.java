package mg.itu.rh.dto.evaluation;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EvaluationDTO {
    private Long idEvaluation;
    private Long idTalent;
    private LocalDate dateEvaluation;
    private List<QuestionEvaluationDTO> evaluations;
}
