package mg.itu.rh.repository;

import mg.itu.rh.entity.AnnonceLangue;
import mg.itu.rh.entity.id.IdAnnonceLangue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnonceLangueRepository extends JpaRepository<AnnonceLangue, IdAnnonceLangue> {
}
