package mg.itu.rh.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidMfaException extends RuntimeException {
    public InvalidMfaException() {
        super("Invalid MFA code or scratch code");
    }
}
