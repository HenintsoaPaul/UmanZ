package mg.itu.rh.entity.question;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.other.POV;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evaluation")
    @JsonView({POV.Public.class,POV.Question.class})
    private Long idEvaluation;

    @Column(name = "date_evaluation")
    @JsonView({POV.Public.class,POV.Question.class})
    private LocalDate dateEvaluation;

    @JsonView({POV.Public.class,POV.Question.class})
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "evaluation",cascade = CascadeType.ALL)
    private List<QuestionTalent> questionTalents;

    @Transient
    @JsonView({POV.Public.class,POV.Question.class})
    private double moyenne;

    public void setMoyenne(){
        double moyenne=0;
        for (QuestionTalent questionTalent:questionTalents) {
            moyenne+=questionTalent.getNote();
        }
        this.moyenne=moyenne/questionTalents.size();
    }
}
