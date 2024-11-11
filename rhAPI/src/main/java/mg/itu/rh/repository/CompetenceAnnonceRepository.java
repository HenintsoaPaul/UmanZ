package mg.itu.rh.repository;

import mg.itu.rh.entity.CompetenceAnnonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetenceAnnonceRepository extends JpaRepository<CompetenceAnnonce, Long> {
    public List<CompetenceAnnonce> findByIdIn( List<Long> ids );
}
