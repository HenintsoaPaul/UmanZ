package mg.itu.rh.repository;

import mg.itu.rh.entity.Poste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PosteRepository extends JpaRepository<Poste, Long> {
    @Query("select c from Poste c")
    public List<Poste> findAllPostes();

    // TODO : décommenter
//    @Query("""
//        SELECT p
//        FROM CategoriesPosition cp
//        JOIN Poste p ON cp.id.idPoste = p.id
//        WHERE cp.pointCategorie > (
//            SELECT cpSub.pointCategorie
//            FROM CategoriesPosition cpSub
//            JOIN Employe e ON e.idPoste = cpSub.id.idPoste
//            AND e.idCategories = cpSub.id.idCategories
//            WHERE e.id = :employeeId
//        )
//        ORDER BY cp.pointCategorie ASC
//    """)
//    List<Poste> findPromotionsByEmployee(@Param("employeeId") Integer employeeId);
}
