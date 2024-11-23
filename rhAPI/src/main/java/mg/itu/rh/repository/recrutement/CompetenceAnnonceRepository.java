package mg.itu.rh.repository.recrutement;

import mg.itu.rh.entity.recrutement.CompetenceAnnonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetenceAnnonceRepository extends JpaRepository<CompetenceAnnonce, Long> {
    List<CompetenceAnnonce> findByIdIn( List<Long> ids );

    @Query( "SELECT ca FROM CompetenceAnnonce ca " +
            "JOIN Annonce a ON a.idAnnonce = ca.annonce.idAnnonce " +
            "WHERE ca.annonce.idAnnonce = :idAnnonce" )
    List<CompetenceAnnonce> findAllByIdAnnonce( @Param( "idAnnonce" ) Long idAnnonce );
}
