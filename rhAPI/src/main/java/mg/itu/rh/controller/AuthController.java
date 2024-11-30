package mg.itu.rh.controller;

import mg.itu.rh.dto.talent.AuthDTO;
import mg.itu.rh.dto.talent.AuthResponseDTO;
import mg.itu.rh.service.talent.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/auth" )
public class AuthController {
    private final AuthService authService;

    public AuthController( AuthService authService ) {
        this.authService = authService;
    }

    @PostMapping
    public AuthResponseDTO authenticate( @RequestBody AuthDTO authDTO ) {
        return authService.findByEmailAndPassword( authDTO );
    }
}
