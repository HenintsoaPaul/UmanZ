package mg.itu.rh.repository.recrutement;

import mg.itu.rh.entity.recrutement.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {
    @Query( "select a from Annonce a " +
            "where a.dateRupture is null and a.dateExpiration > CURRENT_DATE " )
    List<Annonce> findAnnonceAvailable();
}
