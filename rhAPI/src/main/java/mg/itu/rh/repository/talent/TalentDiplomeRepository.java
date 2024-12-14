package mg.itu.rh.repository.talent;

import mg.itu.rh.entity.talent.TalentDiplome;
import mg.itu.rh.entity.id.IdTalentDiplome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TalentDiplomeRepository extends JpaRepository<TalentDiplome,IdTalentDiplome> {
}
