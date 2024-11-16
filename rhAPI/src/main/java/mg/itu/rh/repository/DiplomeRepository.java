package mg.itu.rh.repository;

import mg.itu.rh.entity.Diplome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiplomeRepository extends JpaRepository<Diplome,Long> {
}
