package mg.itu.rh.repository;

import mg.itu.rh.dto.CongeTalentDTO;
import mg.itu.rh.entity.Conge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CongeRepository extends JpaRepository<Conge,Long> {
    @Query("select new mg.itu.rh.entity.Conge(conge.idConge,conge.dateDebut,conge.nbJour,conge.motif) from Conge conge join conge.contrat contrat join contrat.talent talent where talent.idTalent=:idTalent and not conge.dateValidation is null")
    public List<Conge> findCongeByIdTalent(@Param("idTalent") Long idTalent);

    @Query(value = "SELECT SUM(nb_jour) " +
            "FROM conge " +
            "WHERE id_contrat = :idContrat " +
            "AND EXTRACT(YEAR FROM date_debut) = EXTRACT(YEAR FROM CURRENT_DATE) and not date_validation is null" +
            "GROUP BY id_contrat",nativeQuery = true)
    int findNbJourCongePris(@Param("idContrat") Long idContrat);

    @Query("select new mg.itu.rh.dto.CongeTalentDTO(c.contrat.talent,c.nbJour,c.dateDebut,c.motif) from Conge c where not c.dateValidation is null")
    public List<CongeTalentDTO> findAllWithTalent();

}
