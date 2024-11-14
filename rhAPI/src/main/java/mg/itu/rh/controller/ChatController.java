package mg.itu.rh.controller;

import mg.itu.rh.entity.Chat;
import mg.itu.rh.repository.ChatRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatRepository chatRepository;

    public ChatController(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @PostMapping
    public String chat(@RequestParam("message") String message) {
        Chat chatResponse = chatRepository.findResponse(message);
        return chatResponse == null ?
                "Désolé, je ne comprends pas votre question." :
                chatResponse.getReponse();
    }

}
