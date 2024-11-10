package mg.itu.rh.repository;

import mg.itu.rh.entity.ExperiencePoste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExperiencePosteRepository extends JpaRepository<ExperiencePoste, Long> {
    public List<ExperiencePoste> findByIdIn( List<Long> ids);
}
