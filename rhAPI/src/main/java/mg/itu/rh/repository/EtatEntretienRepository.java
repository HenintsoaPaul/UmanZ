package mg.itu.rh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mg.itu.rh.entity.EtatEntretien;

@Repository
public interface EtatEntretienRepository extends JpaRepository<EtatEntretien, Long>{
    @Query("select e from EtatEntretien e where e.niveau=:niveau")
    public EtatEntretien findByNiveau(@Param("niveau") int niveau);
}
