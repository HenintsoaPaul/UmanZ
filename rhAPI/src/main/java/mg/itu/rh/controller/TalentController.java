package mg.itu.rh.controller;

import mg.itu.rh.entity.Talent;
import mg.itu.rh.service.TalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/talent")
public class TalentController {
    @Autowired
    private TalentService talentService;

    @GetMapping("/users")
    public Talent login(@Param("email") String email,@Param("password") String password){
        return talentService.findByEmailAndPassword(email,password);
    }

}
