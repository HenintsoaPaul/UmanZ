package mg.itu.rh.controller.interne;

import mg.itu.rh.entity.interne.TypeRupture;
import mg.itu.rh.service.interne.TypeRuptureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/type_rupture")
public class TypeRuptureController {
    @Autowired
    private TypeRuptureService typeRuptureService;

    @GetMapping
    public List<TypeRupture> findAll(){
        return typeRuptureService.findAll();
    }
}
