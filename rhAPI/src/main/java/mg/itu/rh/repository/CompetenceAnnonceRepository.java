package mg.itu.rh.repository;

import mg.itu.rh.entity.CompetenceAnnonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetenceAnnonceRepository extends JpaRepository<CompetenceAnnonce, Long> {
    List<CompetenceAnnonce> findByIdIn( List<Long> ids );

    @Query( "SELECT ca FROM CompetenceAnnonce ca " +
            "JOIN Entretien e ON e.annonce.idAnnonce = ca.annonce.idAnnonce " +
            "WHERE e.etatEntretien.idEtatEntretien = :etat " +
            "AND ca.annonce.idAnnonce = :idAnnonce" )
    List<CompetenceAnnonce> findAllByIdAnnonceAndEtat( @Param( "idAnnonce" ) Long idAnnonce, @Param( "etat" ) Long etat );
}
