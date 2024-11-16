package mg.itu.rh.repository.question;

import mg.itu.rh.entity.question.QuestionProjet;
import mg.itu.rh.entity.question.QuestionTechnique;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionTechniqueRepository extends JpaRepository<QuestionTechnique,Long> {
    @Query("SELECT q FROM QuestionTechnique q where q.domaine.idDomaine=:idDomaine ORDER BY RANDOM()")
    Page<QuestionTechnique> findAllRandomly(PageRequest pageRequest,@Param("idDomaine") Long idDomaine);
}
