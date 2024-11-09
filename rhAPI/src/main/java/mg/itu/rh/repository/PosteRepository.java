package mg.itu.rh.repository;

import mg.itu.rh.entity.Poste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosteRepository extends JpaRepository<Poste,Long> {
}
