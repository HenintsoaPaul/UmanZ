package mg.itu.rh.service.interne;

import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
//    private final JavaMailSender javaMailSender;

//    @Value( "${spring.mail.username}" )
//    private String fromEmail;

//    public EmailService( JavaMailSender javaMailSender ) {
//        this.javaMailSender = javaMailSender;
//    }

//    public void sendEmail( String toEmail, String subject, String body )
//            throws MessagingException {
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper( mimeMessage, true );
//
//        helper.setFrom( fromEmail );
//        helper.setTo( toEmail );
//        helper.setSubject( subject );
//        helper.setText( body, true );
//
//        javaMailSender.send( mimeMessage );
//    }
}
