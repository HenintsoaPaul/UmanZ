package mg.itu.rh.repository;

import mg.itu.rh.entity.TalentCompetence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface TalentCompetenceRepository extends JpaRepository<TalentCompetence, Long> {
    List<TalentCompetence> findByTalentId(Long idTalent);
    // Optional<TalentCompetence> findByTalentIdAndCompetenceId(Long idTalent, Long idCompetence);
}

