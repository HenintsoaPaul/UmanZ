package mg.itu.rh.repository.talent;

import mg.itu.rh.entity.id.IdExperienceTalent;
import mg.itu.rh.entity.talent.ExperienceTalent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceTalentRepository extends JpaRepository<ExperienceTalent,IdExperienceTalent > {
}
