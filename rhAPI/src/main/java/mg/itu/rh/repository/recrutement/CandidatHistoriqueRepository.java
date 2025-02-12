package mg.itu.rh.repository.recrutement;

import mg.itu.rh.entity.recrutement.CandidatHistorique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidatHistoriqueRepository extends JpaRepository<CandidatHistorique, Long> {
    List<CandidatHistorique> findByTalent_IdTalent(Long idTalent);
    
}
 
