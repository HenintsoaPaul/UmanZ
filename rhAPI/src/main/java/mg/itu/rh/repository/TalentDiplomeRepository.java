package mg.itu.rh.repository;

import mg.itu.rh.entity.TalentDiplome;
import mg.itu.rh.entity.id.IdTalentLangue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TalentDiplomeRepository extends JpaRepository<TalentDiplome, IdTalentLangue> {
}
