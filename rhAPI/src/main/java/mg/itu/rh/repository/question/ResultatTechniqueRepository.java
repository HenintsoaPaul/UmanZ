package mg.itu.rh.repository.question;

import mg.itu.rh.entity.question.ResultatTechnique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResultatTechniqueRepository extends JpaRepository<ResultatTechnique,Long> {
    @Query("select r from ResultatTechnique r where r.talent.idTalent=:idTalent")
    public List<ResultatTechnique> findResultatTechniqueByIdTalent(@Param("idTalent") Long idTalent);
}
