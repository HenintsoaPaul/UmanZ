package mg.itu.rh.dto.interne;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import mg.itu.rh.entity.talent.Talent;
import mg.itu.rh.other.POV;

import java.time.LocalDate;

@Data
public class CongeTalentDTO {
    @JsonView({POV.Public.class})
    private Talent talent;
    @JsonView({POV.Public.class})
    private int nbJour;
    @JsonView({POV.Public.class})
    private LocalDate dateDebut;
    @JsonView({POV.Public.class})
    private String motif;
    @JsonView({POV.Public.class})
    private LocalDate dateFin;

    public CongeTalentDTO(Talent talent,int nbJour,LocalDate dateDebut,String motif){
        this.setTalent(talent);
        this.setNbJour(nbJour);
        this.setDateDebut(dateDebut);
        this.setMotif(motif);
        this.setDateFin(dateDebut.plusDays(nbJour));
    }
}
