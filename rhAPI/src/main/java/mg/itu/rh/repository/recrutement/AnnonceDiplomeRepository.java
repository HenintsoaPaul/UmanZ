package mg.itu.rh.repository.recrutement;

import mg.itu.rh.entity.recrutement.AnnonceDiplome;
import mg.itu.rh.entity.id.IdAnnonceLangue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnonceDiplomeRepository extends JpaRepository<AnnonceDiplome, IdAnnonceLangue> {
}
