package mg.itu.rh.service.question;

import jakarta.transaction.Transactional;
import mg.itu.rh.dto.QuestionReponseDTO;
import mg.itu.rh.dto.ResultatTechniqueDTO;
import mg.itu.rh.entity.question.QuestionTechnique;
import mg.itu.rh.entity.question.Reponse;
import mg.itu.rh.entity.question.ResultatTechnique;
import mg.itu.rh.repository.question.ResultatTechniqueRepository;
import mg.itu.rh.service.TalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResultatTechniqueService {
    private final ResultatTechniqueRepository resultatTechniqueRepository;

    private final TalentService talentService;

    @Autowired
    private ReponseService reponseService;

    public ResultatTechniqueService(ResultatTechniqueRepository resultatTechniqueRepository, TalentService talentService) {
        this.resultatTechniqueRepository = resultatTechniqueRepository;
        this.talentService = talentService;
    }

    public List<ResultatTechnique> findResultatTechniqueByIdTalent(Long idTalent){
        return resultatTechniqueRepository.findResultatTechniqueByIdTalent(idTalent);
    }

    @Transactional
    public ResultatTechnique save(ResultatTechniqueDTO resultat){
        return save(createResultatTechnique(resultat));
    }

    @Transactional
    public ResultatTechnique save(ResultatTechnique resultat){
        return resultatTechniqueRepository.save(resultat);
    }

    @Transactional
    public ResultatTechnique createResultatTechnique(ResultatTechniqueDTO resultat){
        ResultatTechnique resultatTechnique=new ResultatTechnique();
        resultatTechnique.setDateResultat(Timestamp.valueOf(LocalDateTime.now()));
        resultatTechnique.setTalent(talentService.findById(resultat.getIdTalent()));
        resultatTechnique.setReponses(getListQuestionReponse(resultat.getQuestionReponses()));
        return resultatTechnique;
    }

    @Transactional
    public List<Reponse> getListQuestionReponse(List<QuestionReponseDTO> questionReponses){
        List<Reponse> reponses=new ArrayList<Reponse>();
        for (int i = 0; i < questionReponses.size(); i++) {
            Reponse reponse=reponseService.findReponseByIdReponseAndIdQuestion(questionReponses.get(i).getIdReponse(),questionReponses.get(i).getIdQuestionTechnique());
            reponses.add(reponse);
        }
        return reponses;
    }
}
