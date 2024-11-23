package mg.itu.rh.repository.critere;

import mg.itu.rh.entity.critere.Diplome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiplomeRepository extends JpaRepository<Diplome,Long> {
}
