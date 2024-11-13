package mg.itu.rh.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mg.itu.rh.entity.Entretien;
import mg.itu.rh.entity.EtatEntretien;

@Repository
public interface EntretienRepository extends JpaRepository<Entretien, Long> {
    List<Entretien> findByEtatEntretien( EtatEntretien etatEntretien );

    @Query( "SELECT e FROM Entretien e WHERE e.enfant.idEntretien IS NULL" )
    List<Entretien> findAllWhereEnfantIsNull();

    @Query( "SELECT e FROM Entretien e " +
            "WHERE e.etatEntretien.idEtatEntretien = :etat " +
            "AND e.annonce.idAnnonce = :idAnnonce" )
    List<Entretien> findAllByIdAnnonceAndEtat(
            @Param( "idAnnonce" ) Long idAnnonce,
            @Param( "etat" ) Long etat
    );

    @Query( "SELECT e FROM Entretien e " +
            "WHERE e.etatEntretien.idEtatEntretien = 2 " +
            "AND e.annonce.idAnnonce = :idAnnonce " +
            "AND e.talent.idTalent = :idTalent" )
    Entretien findCandidatureByAnnonceAndTalent(
            @Param( "idAnnonce" ) Long idAnnonce,
            @Param( "idTalent" ) Long idTalent
    );
}
