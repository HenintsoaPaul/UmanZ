package mg.itu.rh.repository.recrutement;

import mg.itu.rh.entity.recrutement.PosteLangue;
import mg.itu.rh.entity.id.IdPosteLangue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnonceLangueRepository extends JpaRepository<PosteLangue, IdPosteLangue> {
}
