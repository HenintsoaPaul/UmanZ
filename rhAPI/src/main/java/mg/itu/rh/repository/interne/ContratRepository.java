package mg.itu.rh.repository.interne;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mg.itu.rh.entity.interne.Contrat;

@Repository
public interface ContratRepository extends JpaRepository<Contrat,Long> {

    @Query("SELECT c FROM Contrat c join c.talent t WHERE c.idContrat NOT IN (SELECT r.contrat.idContrat FROM Rupture r join r.contrat) and t.idTalent=:idTalent and (c.dateFin > CURRENT_DATE OR c.dateFin is NULL)")
    public Optional<Contrat> findActualContratByIdTalent(@Param("idTalent") Long idTalent);

    @Query("SELECT c FROM Contrat c")
    public List<Contrat> findAllContrat();
}
