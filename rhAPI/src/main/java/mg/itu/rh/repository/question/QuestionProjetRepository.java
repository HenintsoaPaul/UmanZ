package mg.itu.rh.repository.question;

import mg.itu.rh.entity.question.QuestionProjet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionProjetRepository extends JpaRepository<QuestionProjet,Long> {
    @Query("SELECT q FROM QuestionProjet q ORDER BY RANDOM()")
    Page<QuestionProjet> findAllRandomly(PageRequest pageRequest);

}
