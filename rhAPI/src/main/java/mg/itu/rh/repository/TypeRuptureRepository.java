package mg.itu.rh.repository;

import mg.itu.rh.entity.TypeRupture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface TypeRuptureRepository extends JpaRepository<TypeRupture,Long> {
}
