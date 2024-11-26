package mg.itu.rh.dto.interne;

import lombok.Data;
import mg.itu.rh.entity.critere.Competence;
import mg.itu.rh.entity.interne.CompetenceCible;
import mg.itu.rh.service.critere.CompetenceService;

@Data
public class CompetenceCibleDTO {
    private Competence competence;
    private int point;

    public CompetenceCible getCompetenceCible( CompetenceService competenceService ) {
        CompetenceCible ca = new CompetenceCible();
        ca.setCompetence( competenceService.findById( competence.getIdCompetence() ) );
        ca.setPointGagne( point );
        return ca;
    }
}
