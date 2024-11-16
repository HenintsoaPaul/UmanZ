package mg.itu.rh.service.question;

import mg.itu.rh.entity.question.Reponse;
import mg.itu.rh.repository.question.ReponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReponseService {
    @Autowired
    private ReponseRepository reponseRepository;

    public Reponse findReponseByIdReponseAndIdQuestion(Long idReponse,Long idQuestion){
        return reponseRepository.findByIdReponseAndIdQuestion(idReponse,idQuestion).orElseThrow(()->new RuntimeException("Question/Reponse non retrouve"));
    }
}
