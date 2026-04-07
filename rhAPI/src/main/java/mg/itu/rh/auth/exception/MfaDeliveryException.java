package mg.itu.rh.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class MfaDeliveryException extends RuntimeException {
    private Exception exception;

    public MfaDeliveryException(Exception exception) {
        super("MFA delivery failed. Try again later");
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }
}
