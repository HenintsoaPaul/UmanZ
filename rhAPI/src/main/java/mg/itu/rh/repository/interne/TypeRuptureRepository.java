package mg.itu.rh.repository.interne;

import mg.itu.rh.entity.interne.TypeRupture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRuptureRepository extends JpaRepository<TypeRupture,Long> {
}
