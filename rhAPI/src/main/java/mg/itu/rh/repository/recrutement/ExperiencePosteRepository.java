package mg.itu.rh.repository.recrutement;

import mg.itu.rh.entity.recrutement.ExperiencePoste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperiencePosteRepository extends JpaRepository<ExperiencePoste, Long> {
    List<ExperiencePoste> findByIdIn( List<Long> ids );

    @Query( "SELECT ep FROM ExperiencePoste ep " +
            "JOIN Annonce a ON a.idAnnonce = ep.annonce.idAnnonce " +
            "WHERE ep.annonce.idAnnonce = :idAnnonce" )
    List<ExperiencePoste> findAllByIdAnnonce( @Param( "idAnnonce" ) Long idAnnonce  );
}
