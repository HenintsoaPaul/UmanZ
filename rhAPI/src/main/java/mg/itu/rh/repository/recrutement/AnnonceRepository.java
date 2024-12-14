package mg.itu.rh.repository.recrutement;

import mg.itu.rh.entity.recrutement.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {
    @Query( "select a from Annonce a " +
            "where a.dateRupture is null and a.dateExpiration > CURRENT_DATE " )
    List<Annonce> findAnnonceAvailable();

    @Query("""
            select a.idAnnonce 
            from Annonce a 
            where 
            a.dateRupture is null 
                and 
            a.dateExpiration > CURRENT_DATE 
                and 
            a.idAnnonce in (select e.annonce.idAnnonce from Entretien e where e.talent.idTalent=:idTalent)
           """)
    public List<Long> findAnnoncePostuledByTalent(@Param("idTalent")Long idTalent);
}
