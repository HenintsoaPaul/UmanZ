package mg.itu.rh.entity.question;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "question_projet")
public class QuestionProjet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_question_projet")
    private Long idQuestionProjet;

    private String question;
}
