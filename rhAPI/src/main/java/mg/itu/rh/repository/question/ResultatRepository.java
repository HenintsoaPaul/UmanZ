package mg.itu.rh.repository.question;

import mg.itu.rh.entity.question.Resultat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResultatRepository extends JpaRepository<Resultat,Long> {
    @Query("select r from Resultat r where r.talent.idTalent=:idTalent")
    public List<Resultat> findResultatTechniqueByIdTalent(@Param("idTalent") Long idTalent);
}
