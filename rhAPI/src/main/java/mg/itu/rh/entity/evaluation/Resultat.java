package mg.itu.rh.entity.evaluation;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.entity.talent.Talent;
import mg.itu.rh.other.POV;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
public class Resultat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resultat")
    @JsonView({POV.Question.class})
    private Long idResultat;

    @JsonView({POV.Question.class})
    private double note;

    @Column(name = "date_resultat")
    @JsonView({POV.Question.class})
    private Timestamp dateResultat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_talent")
    @JsonView({POV.Question.class})
    private Talent talent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_domaine")
    private Domaine domaine;

    @Transient
    private List<Reponse> reponses;

    public void setReponses(List<Reponse> reponses){
        this.reponses=reponses;
        this.setNote();
        this.setDomaine();
    }

    protected void setNote(){
        double note=0;
        for (Reponse reponse:reponses) {
            note+=reponse.getNote();
        }
        this.setNote(note/reponses.size());
    }
    protected void setDomaine(){
        if(reponses.size()!=0){
            this.setDomaine(reponses.get(0).getQuestion().getDomaine());
        }
    }
}
