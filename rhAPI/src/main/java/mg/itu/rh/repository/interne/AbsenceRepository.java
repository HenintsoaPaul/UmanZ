package mg.itu.rh.repository.interne;

import mg.itu.rh.entity.interne.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {

    @Query("SELECT COUNT(a) " +
        "FROM Absence a " +
        "WHERE a.contrat.idContrat = :idContrat " +
        "   AND EXTRACT(MONTH FROM a.dateAbsence) = :mois " +
        "   AND EXTRACT(YEAR FROM a.dateAbsence)  = :annee")
    long countByContratAndMoisAndAnnee(
        @Param("idContrat") Long idContrat, @Param("mois") int mois, @Param("annee") int annee);
}
