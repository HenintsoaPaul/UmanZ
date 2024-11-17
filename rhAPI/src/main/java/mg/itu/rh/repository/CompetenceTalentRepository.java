package mg.itu.rh.repository;

import mg.itu.rh.entity.CompetenceTalent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetenceTalentRepository extends JpaRepository<CompetenceTalent, Long> {
}