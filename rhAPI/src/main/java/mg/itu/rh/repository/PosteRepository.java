package mg.itu.rh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mg.itu.rh.entity.interne.Poste;

@Repository
public interface PosteRepository extends JpaRepository<Poste, Long> {

//    @Query("""
//        SELECT p
//FROM CategoriesPosition cp
//JOIN Poste p ON cp.poste.idPoste = p.idPoste
//WHERE cp.pointCategorie > (
//    SELECT cpSub.pointCategorie
//    FROM CategoriesPosition cpSub
//    JOIN Talent t ON t.categoriesPosition.poste.idPoste = cpSub.poste.idPoste
//    AND t.categoriesPosition.id.idCategories = cpSub.id.idCategories
//    WHERE t.idTalent = :idTalent
//)
//ORDER BY cp.pointCategorie ASC
//
//    """)
//    List<Poste> findPromotionsByTalent(@Param("idTalent") Integer idTalent);

}

