package mg.itu.rh.repository;

import mg.itu.rh.entity.Competence;
import mg.itu.rh.entity.CompetenceAnnonce;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Long> {
    @Query("select c from Competence c")
    public List<Competence> findAllCompetences();

    public List<CompetenceAnnonce> findAllById(List<Long> ids);
}
