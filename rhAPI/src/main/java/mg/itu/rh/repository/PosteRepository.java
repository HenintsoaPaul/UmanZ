package mg.itu.rh.repository;

import mg.itu.rh.entity.Poste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PosteRepository extends JpaRepository<Poste, Long> {
    @Query("select c from Poste c")
    public List<Poste> findAllPostes();
}
