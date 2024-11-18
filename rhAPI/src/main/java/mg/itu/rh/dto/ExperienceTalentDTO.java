package mg.itu.rh.dto;

import lombok.Data;
import mg.itu.rh.entity.ExperienceTalent;
import mg.itu.rh.entity.Poste;
import mg.itu.rh.service.PosteService;

@Data
public class ExperienceTalentDTO {
    private Poste poste;
    private int ans;

    public ExperienceTalent getExperienceTalent( PosteService posteService ) {
        ExperienceTalent exp = new ExperienceTalent();
        exp.setPoste( posteService.findById( poste.getIdPoste() ) );
        exp.setAns( ans );
        return exp;
    }
}
