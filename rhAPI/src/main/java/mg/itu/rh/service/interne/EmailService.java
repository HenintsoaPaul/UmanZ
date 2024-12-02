package mg.itu.rh.service.interne;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.mail.MessagingException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import jakarta.mail.internet.MimeMessage;
import mg.itu.rh.entity.interne.RenvoiRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Value( "${spring.mail.username}" )
    private String fromEmail;

    public EmailService( JavaMailSender javaMailSender ) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail( String toEmail, String subject, String body )
            throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper( mimeMessage, true );

        helper.setFrom( fromEmail );
        helper.setTo( toEmail );
        helper.setSubject( subject );
        helper.setText( body, true );

        javaMailSender.send( mimeMessage );
    }
}
