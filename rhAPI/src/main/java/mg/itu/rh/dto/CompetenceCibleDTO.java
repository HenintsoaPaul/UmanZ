package mg.itu.rh.dto;

import lombok.Data;
import mg.itu.rh.entity.Competence;
import mg.itu.rh.entity.CompetenceCible;
import mg.itu.rh.service.CompetenceService;

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
