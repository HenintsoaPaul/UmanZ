package mg.itu.rh.repository.interne;

import mg.itu.rh.dto.interne.CongeTalentDTO;
import mg.itu.rh.entity.interne.Conge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CongeRepository extends JpaRepository<Conge, Long> {
    @Query( "select c from Conge c " +
            "where c.contrat.talent.idTalent=:idTalent and c.dateValidation is not null" )
    List<Conge> findCongeByIdTalent( @Param( "idTalent" ) Long idTalent );

    // TODO: mbola tss anle hoe maty ilay conge non pris au bout de 3 ans
    @Deprecated
    @Query( value = "SELECT coalesce(sum(nb_jour), 0) AS nb_conge_pris FROM conge " +
            "WHERE id_contrat = :idContrat AND id_type_conge = 1 AND date_validation is not null"
            , nativeQuery = true )
    int findNbJourCongePayePris( @Param( "idContrat" ) Long idContrat );

    @Query( value = "SELECT coalesce(sum(nb_jour), 0) AS nb_conge_pris FROM conge " +
            "WHERE id_contrat = :idContrat AND id_type_conge = 3 AND extract(YEAR from date_validation) = :year"
            , nativeQuery = true )
    int findNbJourCongeExceptionnelPris( @Param( "idContrat" ) Long idContrat, int year );

    @Query( "select new mg.itu.rh.dto.interne.CongeTalentDTO(c.contrat.talent,c.nbJour,c.dateDebut,c.motif) " +
            "from Conge c " +
            "where c.dateValidation is not null" )
    List<CongeTalentDTO> findAllWithTalent();

    @Query( "select new mg.itu.rh.dto.interne.CongeTalentDTO(c.contrat.talent,c.nbJour,c.dateDebut,c.motif) " +
            "from Conge c " +
            "where c.dateValidation is null" )
    List<CongeTalentDTO> findAllWithTalentNonValide();

    @Query( "SELECT c " +
            "FROM Conge c " +
            "WHERE c.dateValidation is not null " +
            " AND c.contrat.idContrat = :idContrat" )
    List<Conge> findAllValidatedByIdContrat( @Param( "idContrat" ) Long idContrat );
}
