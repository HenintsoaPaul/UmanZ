package mg.itu.rh.controller;

import mg.itu.rh.entity.interne.Poste;
import mg.itu.rh.service.interne.PosteService;

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
