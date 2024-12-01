package mg.itu.rh.dto.talent;

import lombok.Data;
import mg.itu.rh.entity.interne.Poste;
import mg.itu.rh.entity.talent.ExperienceTalent;
import mg.itu.rh.service.interne.PosteService;

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
