package mg.itu.rh.repository;

import mg.itu.rh.entity.Langue;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LangueRepository extends JpaRepository<Langue, Long> {
        Set<Langue> findAllById(Set<Long> ids);
}
