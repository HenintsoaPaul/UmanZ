package mg.itu.rh.repository.evaluation;

import mg.itu.rh.entity.evaluation.Domaine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomaineRepository extends JpaRepository<Domaine,Long> {
}
