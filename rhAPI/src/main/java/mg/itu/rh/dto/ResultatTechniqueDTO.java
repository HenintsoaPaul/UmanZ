package mg.itu.rh.dto;

import lombok.Data;
import mg.itu.rh.entity.question.ResultatTechnique;

import java.time.LocalDate;
import java.util.List;

@Data
public class ResultatTechniqueDTO {
    private Long idTalent;
    private List<QuestionReponseDTO> questionReponses;
}
