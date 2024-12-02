package mg.itu.rh.repository.interne;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mg.itu.rh.entity.interne.Contrat;

@Repository
public interface ContratRepository extends JpaRepository<Contrat, Long> {

    @Query( "SELECT c FROM Contrat c join c.talent t WHERE c.idContrat NOT IN (SELECT r.contrat.idContrat FROM Rupture r join r.contrat) and t.idTalent=:idTalent and c.dateFin > CURRENT_DATE" )
    Optional<Contrat> findActualContratByIdTalent( @Param( "idTalent" ) Long idTalent );

    //select * from contrat where date_debut in (select min(date_debut) from contrat where id_talent=1)
    @Query( "select c " +
            "from Contrat c " +
            "where c.dateDebut in (select min(c.dateDebut) from Contrat c where c.talent.idTalent=:idTalent)" )
    Optional<Contrat> findContratEmbauche( @Param( "idTalent" ) Long idTalent );

    @Query( "select c " +
            "from Contrat c " +
            "where c.dateDebut <= :date " +
            "   and (:date <= c.dateFin or c.dateFin is null) " +
            "   and not c.idContrat in (select r.contrat.idContrat from Rupture r where r.dateRupture < :date) " +
            "   and c.talent.idTalent=:idTalent" )
    Optional<Contrat> findContratByDateTalent( @Param( "date" ) LocalDate date, @Param( "idTalent" ) Long idTalent );

    @Query( "SELECT c " +
            "FROM Contrat c " +
            "WHERE c.dateDebut <= :date " +
            "   AND (:date <= c.dateFin or c.dateFin is null) " +
            "   AND not c.idContrat in (SELECT r.contrat.idContrat FROM Rupture r WHERE r.dateRupture <= :date)" )
    List<Contrat> findAllContratEnCoursOnDate( @Param( "date" ) LocalDate date );
}
