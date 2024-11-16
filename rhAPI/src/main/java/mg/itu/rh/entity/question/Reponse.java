package mg.itu.rh.entity.question;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "reponse")
public class Reponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_reponse")
    private Long idReponse;

    private String reponse;

    private double note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_question_technique")
    private QuestionTechnique questionTechnique;
}
