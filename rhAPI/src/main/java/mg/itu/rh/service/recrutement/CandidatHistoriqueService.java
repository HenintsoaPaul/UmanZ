package mg.itu.rh.service.recrutement;

import mg.itu.rh.entity.talent.Talent;
import mg.itu.rh.entity.recrutement.CandidatHistorique;
import mg.itu.rh.repository.recrutement.CandidatHistoriqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CandidatHistoriqueService {

    @Autowired
    private CandidatHistoriqueRepository historiqueRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<CandidatHistorique> getByTalentId(Long talentId) {
        return historiqueRepository.findByTalent_IdTalent(talentId);
    }

    public void saveCandidatHistorique(CandidatHistorique historique) {
        String sql = "INSERT INTO candidat_historique (action, date_action, description, id_talent) " +
                     "VALUES (?, ?, ?, ?)";
    
        String action = historique.getAction();
        LocalDateTime dateAction = historique.getDateAction();
        String description = historique.getDescription();
        Long idTalent = historique.getCandidat().getIdTalent();
    
        System.out.println("Insertion dans la base de données avec les valeurs :");
        System.out.println("Action : " + action);
        System.out.println("Date Action : " + dateAction);
        System.out.println("Description : " + description);
        System.out.println("ID Talent : " + idTalent);
    
        jdbcTemplate.update(sql, action, dateAction, description, idTalent);
    }    

    public void ajouterHistoriqueEtNotifier(Talent candidat, String action, String description) {
        CandidatHistorique historique = new CandidatHistorique();
        historique.setCandidat(candidat);
        historique.setAction(action);
        historique.setDescription(description);
        historique.setDateAction(LocalDateTime.now());

        saveCandidatHistorique(historique);
    }

}
