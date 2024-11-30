package mg.itu.rh.controller;

import mg.itu.rh.entity.interne.Chat;
import mg.itu.rh.repository.ChatRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatRepository chatRepository;

    public ChatController(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @PostMapping
    public String chat(@RequestBody Map<String, String> payload) {
        String message = payload.get("message");
        Chat chatResponse = chatRepository.findResponse(message);
        return chatResponse == null ?
                "Désolé, je ne comprends pas votre question." :
                chatResponse.getReponse();
    }

}
