package mg.itu.rh.service.evaluation;

import mg.itu.rh.entity.evaluation.Question;
import mg.itu.rh.repository.evaluation.QuestionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionTechniqueRepository;

    public QuestionService(QuestionRepository questionTechniqueRepository) {
        this.questionTechniqueRepository = questionTechniqueRepository;
    }

    public List<Question> findAllRandomlyByIdDomaine(Long idDomaine){
        return questionTechniqueRepository.findAllRandomly(PageRequest.of(0,5),idDomaine).getContent();
    }
}
