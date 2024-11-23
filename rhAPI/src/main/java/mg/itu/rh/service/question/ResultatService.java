package mg.itu.rh.service.question;

import jakarta.transaction.Transactional;
import mg.itu.rh.dto.QuestionReponseDTO;
import mg.itu.rh.dto.ResultatDTO;
import mg.itu.rh.entity.question.Reponse;
import mg.itu.rh.entity.question.Resultat;
import mg.itu.rh.repository.question.ResultatRepository;
import mg.itu.rh.service.TalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResultatService {
    private final ResultatRepository resultatTechniqueRepository;

    private final TalentService talentService;

    @Autowired
    private ReponseService reponseService;

    public ResultatService(ResultatRepository resultatTechniqueRepository, TalentService talentService) {
        this.resultatTechniqueRepository = resultatTechniqueRepository;
        this.talentService = talentService;
    }

    public List<Resultat> findResultatTechniqueByIdTalent(Long idTalent){
        return resultatTechniqueRepository.findResultatTechniqueByIdTalent(idTalent);
    }

    @Transactional
    public Resultat save(ResultatDTO resultat){
        return save(createResultatTechnique(resultat));
    }

    @Transactional
    public Resultat save(Resultat resultat){
        return resultatTechniqueRepository.save(resultat);
    }

    @Transactional
    public Resultat createResultatTechnique(ResultatDTO resultat){
        Resultat resultatTechnique=new Resultat();
        resultatTechnique.setDateResultat(Timestamp.valueOf(LocalDateTime.now()));
        resultatTechnique.setTalent(talentService.findById(resultat.getIdTalent()));
        resultatTechnique.setReponses(getListQuestionReponse(resultat.getQuestionReponses()));
        return resultatTechnique;
    }

    @Transactional
    public List<Reponse> getListQuestionReponse(List<QuestionReponseDTO> questionReponses){
        List<Reponse> reponses=new ArrayList<Reponse>();
        for (int i = 0; i < questionReponses.size(); i++) {
            Reponse reponse=reponseService.findReponseByIdReponseAndIdQuestion(questionReponses.get(i).getIdReponse(),questionReponses.get(i).getIdQuestion());
            reponses.add(reponse);
        }
        return reponses;
    }
}
