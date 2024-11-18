package mg.itu.rh.service.question;

import mg.itu.rh.entity.question.QuestionTechnique;
import mg.itu.rh.repository.question.QuestionTechniqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionTechniqueService {
    private final QuestionTechniqueRepository questionTechniqueRepository;

    public QuestionTechniqueService(QuestionTechniqueRepository questionTechniqueRepository) {
        this.questionTechniqueRepository = questionTechniqueRepository;
    }

    public List<QuestionTechnique> findAllRandomlyByIdDomaine(Long idDomaine){
        return questionTechniqueRepository.findAllRandomly(PageRequest.of(0,5),idDomaine).getContent();
    }
}
