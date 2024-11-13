package mg.itu.rh.dto;

import lombok.Data;
import mg.itu.rh.entity.ExperiencePoste;
import mg.itu.rh.entity.Poste;
import mg.itu.rh.service.PosteService;

@Data
public class ExperiencePosteDTO {
    private Poste poste;
    private int ans;

    public ExperiencePoste getExperiencePoste( PosteService posteService ) {
        ExperiencePoste exp = new ExperiencePoste();
        exp.setPoste( posteService.findById( poste.getIdPoste() ) );
        exp.setAns( ans );
        return exp;
    }
}
