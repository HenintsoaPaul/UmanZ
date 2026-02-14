package mg.itu.rh.auth;

import mg.itu.rh.auth.exception.TalentNotFoundException;

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
    public LoginResponse authenticate(@RequestBody LoginRequest authDTO) {
        try {
            return authService.findByEmailAndPassword(authDTO);
        } catch (TalentNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}