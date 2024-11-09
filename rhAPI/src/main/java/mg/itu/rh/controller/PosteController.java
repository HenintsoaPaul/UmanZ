package mg.itu.rh.controller;

import mg.itu.rh.entity.Poste;
import mg.itu.rh.service.PosteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postes")
public class PosteController {

    @Autowired
    private PosteService posteService;

    @GetMapping
    public List<Poste> getAllPostes() {
        return posteService.findAll();
    }
}
