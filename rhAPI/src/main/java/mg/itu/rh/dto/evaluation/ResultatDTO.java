package mg.itu.rh.dto.evaluation;

import lombok.Data;

import java.util.List;

@Data
public class ResultatDTO {
    private Long idTalent;
    private List<QuestionReponseDTO> questionReponses;
}
