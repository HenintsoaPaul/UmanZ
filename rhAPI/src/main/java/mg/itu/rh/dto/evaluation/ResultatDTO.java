package mg.itu.rh.dto.evaluation;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResultatDTO {
    private Long idTalent;
    private List<QuestionReponseDTO> questionReponses;
}
