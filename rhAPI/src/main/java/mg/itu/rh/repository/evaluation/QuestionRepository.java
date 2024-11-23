package mg.itu.rh.repository.evaluation;

import mg.itu.rh.entity.evaluation.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    @Query("SELECT q FROM Question q where q.domaine.idDomaine=:idDomaine ORDER BY RANDOM()")
    Page<Question> findAllRandomly(PageRequest pageRequest, @Param("idDomaine") Long idDomaine);
}
