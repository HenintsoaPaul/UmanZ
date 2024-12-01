package mg.itu.rh.repository.talent;

import mg.itu.rh.entity.talent.Talent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface TalentRepository extends JpaRepository<Talent,Long> {
    @Query("select t from Talent t where t.mail=:email and t.password=:password")
    Optional<Talent> findByEmailAndPassword( @Param( "email" ) String email, @Param( "password" ) String password );

    @Query("select t from Talent t where t.mail=:email")
    Optional<Talent> findByEmail( @Param( "email" ) String email );

//    List<Talent> findByCategoriesPosition_Id_IdCategories(Long idCategories);
}
