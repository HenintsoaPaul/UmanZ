package mg.itu.rh.repository.recrutement;

import mg.itu.rh.entity.recrutement.Compatibilite;
import mg.itu.rh.entity.id.IdTalentPoste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompatibiliteRepository extends JpaRepository<Compatibilite, IdTalentPoste> {
    @Query("select c from Compatibilite c join c.poste p")
    public List<Compatibilite> findAllByIdTalentAnnonce(@Param("idTalent") Long idTalent);
}
