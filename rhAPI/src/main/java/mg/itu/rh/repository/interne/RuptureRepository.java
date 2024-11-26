package mg.itu.rh.repository.interne;

import mg.itu.rh.entity.interne.Rupture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuptureRepository extends JpaRepository<Rupture,Long> {
}
