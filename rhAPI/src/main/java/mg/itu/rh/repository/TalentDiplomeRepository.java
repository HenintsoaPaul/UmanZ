package mg.itu.rh.repository;

import mg.itu.rh.entity.TalentDiplome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TalentDiplomeRepository extends JpaRepository<TalentDiplome, Long> {
    List<TalentDiplome> findByTalentId(Long idTalent);
}


