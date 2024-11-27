package mg.itu.rh.dto.poste;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import mg.itu.rh.entity.critere.Competence;
import mg.itu.rh.entity.recrutement.CompetencePoste;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.critere.CompetenceService;

@Data
public class CompetencePosteDTO {
    @JsonView({POV.Public.class})
    private Competence competence;
    @JsonView({POV.Public.class})
    private int point;

    public CompetencePoste getCompetenceAnnonce(CompetenceService competenceService ) {
        CompetencePoste ca = new CompetencePoste();
        ca.setCompetence( competenceService.findById( competence.getIdCompetence() ) );
        ca.setPoint( point );
        return ca;
    }
}
