package mg.itu.rh.auth;

import mg.itu.rh.auth.exception.TalentNotFoundException;
import mg.itu.rh.dto.talent.AuthDTO;
import mg.itu.rh.dto.talent.AuthResponseDTO;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public AuthResponseDTO authenticate(@RequestBody AuthDTO authDTO) {
        try {
            return authService.findByEmailAndPassword(authDTO);
        } catch (TalentNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}