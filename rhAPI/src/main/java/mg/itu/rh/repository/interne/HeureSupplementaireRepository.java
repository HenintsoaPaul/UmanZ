package mg.itu.rh.repository.interne;

import mg.itu.rh.dto.interne.HeureSupplementaireDTO;
import mg.itu.rh.entity.interne.HeureSupplementaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface HeureSupplementaireRepository extends JpaRepository<HeureSupplementaire, Long> {

    @Query("SELECT new mg.itu.rh.dto.interne.HeureSupplementaireDTO(hs.tauxMajoration, SUM(hs.nbHeure)) " +
        "FROM HeureSupplementaire hs " +
        "JOIN hs.contrat c " +
        "WHERE hs.contrat.idContrat   = :idContrat " +
        "   AND MONTH(hs.dateHeureDebut) = :mois " +
        "   AND YEAR(hs.dateHeureDebut)  = :annee " +
        "GROUP BY hs.tauxMajoration")
    List<HeureSupplementaireDTO> findSumHeuresAndMontantByContratAndMoisAndAnnee(
        @Param("idContrat") Long idContrat, @Param("mois") int mois, @Param("annee") int annee);

    @Query(value = "SELECT COALESCE(SUM(hs.nb_heure), 0)\n" +
        "FROM heure_supplementaire hs\n" +
        "WHERE hs.id_contrat = :idContrat\n" +
        "  AND hs.date_heure_debut >= DATE_TRUNC('week', CAST(:dateHeureDebut AS TIMESTAMP))\n" +
        "  AND hs.date_heure_debut < DATE_TRUNC('week', CAST(:dateHeureDebut AS TIMESTAMP)) + INTERVAL '1 week'",
        nativeQuery = true)
    Double findTotalHeuresForWeekByContrat(
        @Param("idContrat") Long idContrat, @Param("dateHeureDebut") LocalDateTime dateHeureDebut);
}
