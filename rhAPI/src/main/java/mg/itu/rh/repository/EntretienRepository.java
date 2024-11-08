package mg.itu.rh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mg.itu.rh.entity.Entretien;
import mg.itu.rh.entity.EtatEntretien;

@Repository
public interface EntretienRepository extends JpaRepository<Entretien, Long> {
    public List<Entretien> findByEtatEntretien(EtatEntretien etatEntretien);
}
