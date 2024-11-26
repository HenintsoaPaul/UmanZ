package mg.itu.rh.repository.recrutement;

import mg.itu.rh.entity.recrutement.PosteDiplome;
import mg.itu.rh.entity.id.IdPosteLangue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnonceDiplomeRepository extends JpaRepository<PosteDiplome, IdPosteLangue> {
}
