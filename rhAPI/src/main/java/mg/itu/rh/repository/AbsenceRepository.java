package mg.itu.rh.repository;

import mg.itu.rh.entity.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {
    @Query("select a from Absence a")
    public List<Absence> findAllAbsences();
}
