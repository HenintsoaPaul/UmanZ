package mg.itu.rh.entity.question;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.other.POV;

import java.util.List;

@Entity
@Data
@Table(name = "question_technique")
public class QuestionTechnique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_question_technique")
    @JsonView({POV.Public.class})
    private Long idQuestionTechnique;

    @JsonView({POV.Public.class})
    private String question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_domaine")
    private Domaine domaine;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "questionTechnique")
    @JsonView({POV.Public.class})
    private List<Reponse> reponses;
}
