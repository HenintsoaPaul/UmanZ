package mg.itu.rh.dto;

import lombok.Data;
import mg.itu.rh.entity.Competence;
import mg.itu.rh.entity.CompetenceTalent;
import mg.itu.rh.service.CompetenceService;

@Data
public class CompetenceTalentDTO {
    private Competence competence;
    private int point;

    public CompetenceTalent getCompetenceTalent( CompetenceService competenceService ) {
        CompetenceTalent ca = new CompetenceTalent();
        ca.setCompetence( competenceService.findById( competence.getIdCompetence() ) );
        ca.setPoint( point );
        return ca;
    }
}
