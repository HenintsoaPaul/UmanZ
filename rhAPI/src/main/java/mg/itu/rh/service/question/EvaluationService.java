package mg.itu.rh.service.question;

import mg.itu.rh.dto.EvaluationDTO;
import mg.itu.rh.dto.QuestionEvaluationDTO;
import mg.itu.rh.entity.question.Evaluation;
import mg.itu.rh.entity.question.QuestionEvaluation;
import mg.itu.rh.repository.question.EvaluationRepository;
import mg.itu.rh.service.TalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EvaluationService {
    private final EvaluationRepository evaluationRepository;

    private final TalentService talentService;

    @Autowired
    private QuestionProjetService questionProjetService;

    public EvaluationService(EvaluationRepository evaluationRepository, TalentService talentService) {
        this.evaluationRepository = evaluationRepository;
        this.talentService = talentService;
    }

    public Evaluation findById(Long idEvaluation){
        return evaluationRepository.findById(idEvaluation).orElseThrow(()->new RuntimeException("Evaluation non reconnue"));
    }

    public List<Evaluation> findEvaluationNotNoted(){
        return evaluationRepository.findEvaluationNotNoted();
    }

    public Evaluation findEvaluationNotNoted(Long idEvaluation){
        return evaluationRepository.findEvaluationNotNoted(idEvaluation).orElseThrow(()->new RuntimeException("Evaluation non  ou dejà note"));
    }

    public List<Evaluation> findEvaluationNoted(Long idTalent){
        List<Evaluation> evaluations=evaluationRepository.findEvaluationNoted(idTalent);
        for (Evaluation evaluation:evaluations) {
            evaluation.setMoyenne();
        }
        return evaluations;
    }

    public Evaluation save(Evaluation evaluation){
        return evaluationRepository.save(evaluation);
    }

    public Evaluation save(EvaluationDTO evaluation){
        return save(createEvaluation(evaluation));
    }

    public Evaluation update(EvaluationDTO evaluationDTO){
        Evaluation evaluation=findById(evaluationDTO.getIdEvaluation());
        System.out.println(evaluationDTO.getEvaluations().size()+" "+evaluation.getQuestionEvaluations().size());
        for (int i = 0; i < evaluationDTO.getEvaluations().size(); i++) {
            for (int j = 0; j < evaluation.getQuestionEvaluations().size(); j++) {
                System.out.println(evaluationDTO.getEvaluations().get(i).getIdQuestionEvaluation()+" == "+evaluation.getQuestionEvaluations().get(j).getEvaluation().getIdEvaluation());
                if(evaluationDTO.getEvaluations().get(i).getIdQuestionEvaluation()==evaluation.getQuestionEvaluations().get(j).getEvaluation().getIdEvaluation()){
                    evaluation.getQuestionEvaluations().get(j).setNote(evaluationDTO.getEvaluations().get(i).getNote());
                    break;
                }
            }
        }
        return save(evaluation);
    }

    public Evaluation createEvaluation(EvaluationDTO evaluationDTO){
        Evaluation evaluation=new Evaluation();
        evaluation.setDateEvaluation(LocalDate.now());
        evaluation.setTalent(talentService.findById(evaluationDTO.getIdTalent()));
        evaluation.setQuestionEvaluations(createQuestionEvaluation(evaluationDTO.getEvaluations(),evaluation));
        return evaluation;
    }

    public List<QuestionEvaluation> createQuestionEvaluation(List<QuestionEvaluationDTO> questionEvaluations,Evaluation evaluation){
        List<QuestionEvaluation> valiny=new ArrayList<QuestionEvaluation>();
        for (QuestionEvaluationDTO questDTO:questionEvaluations) {
            QuestionEvaluation questEva=new QuestionEvaluation();
            questEva.setEvaluation(evaluation);
            questEva.setNote(questDTO.getNote());
            questEva.setReponse(questDTO.getReponse());
            questEva.setQuestionProjet(questionProjetService.findById(questDTO.getIdQuestionProjet()));
            valiny.add(questEva);
        }
        return valiny;
    }
}
