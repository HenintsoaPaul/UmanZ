package mg.itu.rh.repository.interne;

import org.springframework.data.jpa.repository.JpaRepository;

import mg.itu.rh.entity.interne.TypeContrat;

public interface TypeContratRepository extends JpaRepository<TypeContrat, Long>{
    
}
