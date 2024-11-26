package mg.itu.rh.dto.talent;

import lombok.Data;
import mg.itu.rh.entity.critere.Competence;
import mg.itu.rh.entity.talent.CompetenceTalent;
import mg.itu.rh.service.critere.CompetenceService;

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
