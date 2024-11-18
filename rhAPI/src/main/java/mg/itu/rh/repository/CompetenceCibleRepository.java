package mg.itu.rh.repository;

import mg.itu.rh.entity.CompetenceCible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetenceCibleRepository extends JpaRepository<CompetenceCible, Long> {
}
