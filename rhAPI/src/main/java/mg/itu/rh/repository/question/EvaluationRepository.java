package mg.itu.rh.repository.question;

import mg.itu.rh.entity.question.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EvaluationRepository extends JpaRepository<Evaluation,Long> {
    @Query("select e from QuestionEvaluation q join q.evaluation e where q.note is null")
    public List<Evaluation> findEvaluationNotNoted();

    @Query("select e from QuestionEvaluation q join q.evaluation e where q.note is null and e.idEvaluation=:idEvaluation")
    public Optional<Evaluation> findEvaluationNotNoted(@Param("idEvaluation") Long idEvaluation);

    @Query("select e from QuestionEvaluation q join q.evaluation e where q.note is not null and e.talent.idTalent=:idTalent")
    public List<Evaluation> findEvaluationNoted(@Param("idTalent")Long idTalent);
}
