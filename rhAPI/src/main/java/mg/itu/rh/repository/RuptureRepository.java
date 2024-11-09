package mg.itu.rh.repository;

import mg.itu.rh.entity.Rupture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuptureRepository extends JpaRepository<Rupture,Long> {
}
