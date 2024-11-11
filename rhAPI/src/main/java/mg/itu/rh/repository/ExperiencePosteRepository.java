package mg.itu.rh.repository;

import mg.itu.rh.entity.ExperiencePoste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExperiencePosteRepository extends JpaRepository<ExperiencePoste, Long> {
    List<ExperiencePoste> findByIdIn( List<Long> ids );

    @Query( "SELECT ep FROM ExperiencePoste ep " +
            "JOIN Entretien e ON e.annonce.idAnnonce = ep.annonce.idAnnonce " +
            "WHERE e.etatEntretien.idEtatEntretien = :etat " +
            "AND ep.annonce.idAnnonce = :idAnnonce" )
    List<ExperiencePoste> findAllByIdAnnonceAndEtat( @Param( "idAnnonce" ) Long idAnnonce, @Param( "etat" ) Long etat );
}
