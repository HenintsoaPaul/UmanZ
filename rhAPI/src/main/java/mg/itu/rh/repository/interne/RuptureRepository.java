package mg.itu.rh.repository.interne;

import mg.itu.rh.entity.interne.Rupture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuptureRepository extends JpaRepository<Rupture, Long> {
    @Query( "SELECT r " +
            "FROM Rupture r " +
            "WHERE r.dateRuptureValidation is null" )
    List<Rupture> findAllPendingRuptures();

    @Query( "SELECT r " +
            "FROM Rupture r " +
            "WHERE r.dateRuptureValidation is null" +
            "   and r.contrat.idContrat = :idContrat" )
    Rupture findAllPendingRupturesOfEmp( @Param( "idContrat" ) Long idContrat );
}
