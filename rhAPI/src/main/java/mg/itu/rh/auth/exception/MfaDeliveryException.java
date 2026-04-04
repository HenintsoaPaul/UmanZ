package mg.itu.rh.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class MfaDeliveryException extends RuntimeException {
    MailException mailException;

    public MfaDeliveryException(MailException mailException) {
        super("MFA delivery failed. Try again later");
        this.mailException = mailException;
    }

    public MailException getMailException() {
        return mailException;
    }
}
