package mg.itu.rh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mg.itu.rh.entity.TypeContrat;
import mg.itu.rh.repository.TypeContratRepository;


@RestController
@RequestMapping("/type")
public class TypeContratController {
    @Autowired
    private TypeContratRepository typeContratRepository;
    @GetMapping
    public List<TypeContrat> getMethodName() {
        return typeContratRepository.findAll();
    }
    
}
