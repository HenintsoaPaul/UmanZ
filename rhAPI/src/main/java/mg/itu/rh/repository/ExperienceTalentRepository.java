package mg.itu.rh.repository;

import mg.itu.rh.entity.ExperienceTalent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceTalentRepository extends JpaRepository<ExperienceTalent, Long> {
}
