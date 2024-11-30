package mg.itu.rh.service.chat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.itu.rh.entity.critere.Competence;
import mg.itu.rh.repository.PosteRepository;
import mg.itu.rh.repository.recrutement.CompetenceRepository;

@Service
public class ChatService {

    @Autowired
    PosteRepository posteRepo;

    @Autowired
    CompetenceRepository competenceRepo;

    public static final String COHERE_API_URL = "https://api.cohere.ai/v1/generate";
    public static final String API_KEY = "jPyVs0wDMDNbq3zEq75GTgtZSdrbsbsyMcoE7D2I"; 

    public String getPrompt(String question){
        List<Competence> listeCompetences = competenceRepo.findAll();
            
        String prompt = String.format("""
                Vous êtes un assistant intelligent spécialisé dans la réponse aux questions des candidats postulant dans mon entreprise en utilisant les données suivantes :
                Reponds au candidats avec le minimum de paragraphe avec un emojie si possible, et fait en sorte que tu discutte directement avec le candidat , joue vraiment un role , le dit pas des mots du genre 'Voici une réponse adaptée pour un candidat : '

                . Etapes pour avoir un Job ici:
                1- Inscription sur notre site
                2- Voir la liste des annonces
                3- Il postule pour un annonce
                4- Envoie de cv pour cette annonce (candidature)
                5- Le candidat attend la validation de son CV pour passer la premiere entretient , si il passe le responsable RH envoie une email au candidat
                6- Passer la premiere entretient(Evaluation Technique) , le respondable RH attribut une note a cette entretient et si c'est une bonne note il passe directement au second entretient
                7- Passer la deuxieme entretient meme processus que le premier mais cette fois ci si il passe , le RH lui donne directement un contrat d'essaie mais pas encore de CDD ou CDI
                9 - Remarque qu'a chaque validation d'entretient il y aura une discussion entre les dirigeants avant de passer a l'etape suivante
                8- Il peut commencer a travailler

                """);
            StringBuilder promptBuilder = new StringBuilder(prompt);            
            
            /* Tout ce que l'IA devrait savoir ici */

            promptBuilder.append(". La liste des principales competences dans l'entreprise: \n");
            for (int i = 0; i < listeCompetences.size() ; i++) {
                promptBuilder.append("\n "+listeCompetences.get(i).getCompetence() +"\n");   
            }      
            //
            promptBuilder.append("\nQuestion : ").append(question);
            promptBuilder.append("\r\n" +
                                "\r\n" +
                                "Veuillez générer une réponse claire et complète, adaptée à un postulant qui souhaite nous intégrés, en utilisant les informations ci-dessus. Si la question ne peut pas être répondue avec les données disponibles, veuillez indiquer poliment que vous ne disposez pas de cette information.");
            
        return promptBuilder.toString();
    }
}
