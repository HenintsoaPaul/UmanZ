package mg.itu.rh.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResultatDTO {
    private Long idTalent;
    private List<QuestionReponseDTO> questionReponses;
}
