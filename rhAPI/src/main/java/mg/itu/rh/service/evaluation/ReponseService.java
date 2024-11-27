package mg.itu.rh.service.evaluation;

import mg.itu.rh.entity.evaluation.Reponse;
import mg.itu.rh.repository.evaluation.ReponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReponseService {
    @Autowired
    private ReponseRepository reponseRepository;

    public Reponse findReponseByIdReponseAndIdQuestion(Long idReponse, Long idQuestion){
        return reponseRepository.findByIdReponseAndIdQuestion(idReponse,idQuestion).orElseThrow(()->new RuntimeException("Question/Reponse non retrouve"));
    }
}
