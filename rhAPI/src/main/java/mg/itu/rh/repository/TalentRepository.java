package mg.itu.rh.repository;

import mg.itu.rh.entity.Talent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TalentRepository extends JpaRepository<Talent,Long> {
    @Query("select t from Talent t where t.mail=:email and t.password=:password")
    public Optional<Talent> findByEmailAndPassword(@Param("email")String email, @Param("password") String password);

    @Query("select t from Talent t where t.mail=:email")
    public Optional<Talent> findByEmail(@Param("email")String email);

}