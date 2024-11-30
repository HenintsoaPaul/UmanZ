package mg.itu.rh.repository.interne;

import mg.itu.rh.entity.interne.HeureSupplementaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface HeureSupplementaireRepository extends JpaRepository<HeureSupplementaire, Long> {

    @Query(value = "SELECT COALESCE(SUM(hs.nb_heure), 0)\n" +
        "FROM heure_supplementaire hs\n" +
        "WHERE hs.id_contrat = :idContrat\n" +
        "  AND hs.date_heure_debut >= date_trunc('week', CAST(:dateHeureDebut AS TIMESTAMP))\n" +
        "  AND hs.date_heure_debut < date_trunc('week', CAST(:dateHeureDebut AS TIMESTAMP)) + INTERVAL '1 week'",
    nativeQuery = true)
    Double findTotalHeuresForWeekByContrat(
        @Param("idContrat") Long idContrat, @Param("dateHeureDebut") LocalDateTime dateHeureDebut);
}
