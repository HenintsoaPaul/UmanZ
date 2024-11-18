package mg.itu.rh.service.question;

import mg.itu.rh.entity.question.QuestionProjet;
import mg.itu.rh.repository.question.QuestionProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionProjetService {
    private final QuestionProjetRepository questionProjetRepository;

    public QuestionProjetService(QuestionProjetRepository questionProjetRepository) {
        this.questionProjetRepository = questionProjetRepository;
    }

    public List<QuestionProjet> findAllRandomly(){
        return questionProjetRepository.findAllRandomly(PageRequest.of(0, 5)).getContent();
    }

    public QuestionProjet findById(Long idQuestionProjet){
        return questionProjetRepository.findById(idQuestionProjet).orElseThrow(()->new RuntimeException("Question non reconnue"));
    }
}
