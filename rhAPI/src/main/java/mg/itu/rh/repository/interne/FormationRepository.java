package mg.itu.rh.repository.interne;

import mg.itu.rh.entity.interne.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FormationRepository extends JpaRepository<Formation, Long> {
    @Query( "SELECT f FROM Formation f WHERE f.dateDebut > current_date and f.estFini = false " )
    List<Formation> findAllDisponible();
}
