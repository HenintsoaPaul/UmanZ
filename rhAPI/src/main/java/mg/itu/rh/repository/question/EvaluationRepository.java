package mg.itu.rh.repository.question;

import mg.itu.rh.entity.question.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation,Long> {
    @Query("select e from QuestionTalent q join q.evaluation e where q.note is null")
    public List<Evaluation> findEvaluationNotNoted();

    @Query("select e from QuestionTalent q join q.evaluation e where q.note is not null and q.talent.idTalent=:idTalent")
    public List<Evaluation> findEvaluationNoted(@Param("idTalent")Long idTalent);
}
