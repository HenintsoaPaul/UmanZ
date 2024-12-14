package mg.itu.rh.repository.evaluation;

import mg.itu.rh.entity.evaluation.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ReponseRepository extends JpaRepository<Reponse,Long> {
    @Query("select r from Reponse r where r.idReponse=:idReponse and r.question.idQuestion=:idQuestion")
    Optional<Reponse> findByIdReponseAndIdQuestion( @Param( "idReponse" ) Long idReponse, @Param( "idQuestion" ) Long idQuestion );
}
