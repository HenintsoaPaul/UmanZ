package mg.itu.rh.dto;

import lombok.Data;
import mg.itu.rh.entity.Competence;
import mg.itu.rh.entity.CompetenceAnnonce;
import mg.itu.rh.service.CompetenceService;

@Data
public class CompetenceAnnonceDTO {
    private Competence competence;
    private int point;

    public CompetenceAnnonce getCompetenceAnnonce( CompetenceService competenceService ) {
        CompetenceAnnonce ca = new CompetenceAnnonce();
        ca.setCompetence( competenceService.findById( competence.getIdCompetence() ) );
        ca.setPoint( point );
        return ca;
    }
}
