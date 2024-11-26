package mg.itu.rh.repository.interne;

import mg.itu.rh.entity.interne.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    @Query( "SELECT p FROM Participation p WHERE p.formation.idFormation = :id" )
    List<Participation> findAllByFormation( @Param( "id" ) Long id );
}
