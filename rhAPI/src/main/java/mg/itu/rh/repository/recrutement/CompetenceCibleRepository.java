package mg.itu.rh.repository.recrutement;

import mg.itu.rh.entity.interne.CompetenceCible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetenceCibleRepository extends JpaRepository<CompetenceCible, Long> {
}
