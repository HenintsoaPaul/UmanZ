package mg.itu.rh.repository;

import mg.itu.rh.entity.TalentCompetence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mg.itu.rh.entity.id.IdTalentCompetence;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.*;

@Repository
public interface TalentCompetenceRepository extends JpaRepository<TalentCompetence, Long> {
    List<TalentCompetence> findByTalentId(Long idTalent);
    // Optional<TalentCompetence> findByTalentIdAndCompetenceId(Long idTalent, Long idCompetence);

    @Query( "SELECT tc FROM TalentCompetence tc " +
            "WHERE tc.competence.idCompetence = :idCompetence " +
            "AND tc.talent.idTalent = :idTalent" )
    TalentCompetence findByIdCompetenceAndIdTalent( @Param( "idCompetence" ) Long idCompetence, @Param( "idTalent" ) Long idTalent );
}
