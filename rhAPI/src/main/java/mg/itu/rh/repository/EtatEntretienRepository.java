package mg.itu.rh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mg.itu.rh.entity.EtatEntretien;

@Repository
public interface EtatEntretienRepository extends JpaRepository<EtatEntretien, Long>{
    
}
