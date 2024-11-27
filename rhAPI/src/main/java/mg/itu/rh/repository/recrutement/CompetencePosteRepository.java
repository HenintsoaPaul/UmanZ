package mg.itu.rh.repository.recrutement;

import mg.itu.rh.entity.id.IdCompetencePoste;
import mg.itu.rh.entity.recrutement.CompetencePoste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetencePosteRepository extends JpaRepository<CompetencePoste, IdCompetencePoste> {
    List<CompetencePoste> findByIdIn(List<Long> ids );

    @Query( "SELECT cp FROM CompetencePoste cp " +
            "JOIN Poste p ON p.idPoste = cp.poste.idPoste " +
            "WHERE cp.poste.idPoste = :idPoste" )
    List<CompetencePoste> findAllByIdPoste(@Param( "idPoste" ) Long idPoste );
}
