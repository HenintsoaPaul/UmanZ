package mg.itu.rh.service.question;

import mg.itu.rh.entity.question.Evaluation;
import mg.itu.rh.repository.question.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationService {
    private final EvaluationRepository evaluationRepository;

    public EvaluationService(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }

    public List<Evaluation> findEvaluationNotNoted(){
        return evaluationRepository.findEvaluationNotNoted();
    }

    public List<Evaluation> findEvaluationNoted(Long idTalent){
        List<Evaluation> evaluations=evaluationRepository.findEvaluationNoted(idTalent);
        for (Evaluation evaluation:evaluations) {
            evaluation.setMoyenne();
        }
        return evaluations;
    }
}
