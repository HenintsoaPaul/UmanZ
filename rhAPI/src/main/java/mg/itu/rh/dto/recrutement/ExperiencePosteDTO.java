package mg.itu.rh.dto.recrutement;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import mg.itu.rh.entity.recrutement.ExperiencePoste;
import mg.itu.rh.entity.Poste;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.PosteService;

@Data
public class ExperiencePosteDTO {
    @JsonView({POV.Public.class})
    private Poste poste;
    @JsonView({POV.Public.class})
    private int ans;

    public ExperiencePoste getExperiencePoste( PosteService posteService ) {
        ExperiencePoste exp = new ExperiencePoste();
        exp.setPoste( posteService.findById( poste.getIdPoste() ) );
        exp.setAns( ans );
        return exp;
    }
}
