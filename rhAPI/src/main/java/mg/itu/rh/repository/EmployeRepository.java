package mg.itu.rh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import mg.itu.rh.entity.Employe;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {
//    List<Employe> findByCategory_IdCategories(Long idCategories);
}
