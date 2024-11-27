package mg.itu.rh.repository.recrutement;

import mg.itu.rh.entity.recrutement.Compatibilite;
import mg.itu.rh.entity.id.IdTalentAnnonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompatibiliteRepository extends JpaRepository<Compatibilite, IdTalentAnnonce> {
    @Query("select c from Compatibilite c join c.annonce a where c.talent.idTalent=:idTalent and a.dateRupture is null and a.dateExpiration > CURRENT_DATE")
    public List<Compatibilite> findAllByIdTalentAnnonce(@Param("idTalent") Long idTalent);
}
