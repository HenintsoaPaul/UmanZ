package mg.itu.rh.repository.recrutement;

import mg.itu.rh.entity.recrutement.AnnonceLangue;
import mg.itu.rh.entity.id.IdAnnonceLangue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnonceLangueRepository extends JpaRepository<AnnonceLangue, IdAnnonceLangue> {
}
