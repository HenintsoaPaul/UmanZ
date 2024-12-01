package mg.itu.rh.controller;

import mg.itu.rh.entity.interne.Chat;
import mg.itu.rh.repository.ChatRepository;
import mg.itu.rh.service.chat.ChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatController {

    //private final ChatRepository chatRepository;

    @Autowired
    ChatService chatService;

    // public ChatController(ChatRepository chatRepository) {
    //     this.chatRepository = chatRepository;
    // }

    // @PostMapping
    // public String chat(@RequestBody Map<String, String> payload) {
    //     String message = payload.get("message");
    //     Chat chatResponse = chatRepository.findResponse(message);
    //     return chatResponse == null ?
    //             "Désolé, je ne comprends pas votre question." :
    //             chatResponse.getReponse();
    // }

    @PostMapping
    public ResponseEntity<?> askQuestion(@RequestBody Map<String, Object> requestData) {
        try {
            // Récupérer les données de la requête
            String question = (String) requestData.get("message");
            if (question == null) {
                return ResponseEntity.badRequest().body("Question manquante");
            }
            
            String prompt = chatService.getPrompt(question);
            // Créer le corps de la requête pour l'API Cohere
            Map<String, Object> jsonRequest = new HashMap<>();
            jsonRequest.put("model", "command-r-plus");
            jsonRequest.put("prompt", prompt);
            jsonRequest.put("max_tokens", 1000);
            jsonRequest.put("temperature", 0.7);

            // Convertir en JSON avec Jackson
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonBody = objectMapper.writeValueAsString(jsonRequest);

            // Préparer les en-têtes HTTP
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + ChatService.API_KEY);

            // Construire la requête avec RestTemplate
            HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map> response = restTemplate.exchange(ChatService.COHERE_API_URL, HttpMethod.POST, requestEntity, Map.class);

            // Extraire la réponse générée par Cohere
            Map<String, Object> responseBody = response.getBody();
            if (responseBody == null || !responseBody.containsKey("generations")) {
                return ResponseEntity.status(500).body("Réponse inattendue de l'API Cohere");
            }
            
            Object generations = responseBody.get("generations");
            if (generations instanceof ArrayList) {
                ArrayList<?> generationsList = (ArrayList<?>) generations;
                if (!generationsList.isEmpty() && generationsList.get(0) instanceof Map) {
                    Map<?, ?> generation = (Map<?, ?>) generationsList.get(0);
                    String generatedText = (String) generation.get("text");
                    return ResponseEntity.ok(generatedText.trim());
                }
            }
            return ResponseEntity.status(500).body("Format de réponse invalide");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erreur avec l'API Cohere : " + e.getMessage());
        }
    }

}
