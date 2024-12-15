package mg.itu.rh.service.interne;

import lombok.RequiredArgsConstructor;
import mg.itu.rh.dto.rupture.RuptureRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;

import jakarta.mail.MessagingException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import jakarta.mail.internet.MimeMessage;

import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final PdfGenerationService pdfGenerationService;

    @Value( "${spring.mail.username}" )
    private String fromEmail;

    public void writeEmail( String toEmail, String subject, RuptureRequest ruptureRequest )
            throws MessagingException {
        String body = String.format(
                "<h1>%s</h1>" +
                        "<p><strong>Motif :</strong> %s</p>" +
                        "<p><strong>Date :</strong> %s</p>",
                subject,
                ruptureRequest.getMotif(),
                ruptureRequest.getDate()
        );
        this.sendEmail( toEmail, subject, body );
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

    public void sendEmailWithAttachments( String toEmail, String name )
            throws Exception {
        ByteArrayOutputStream certPdf = pdfGenerationService.generateCertificate( name );
        ByteArrayOutputStream unemploymentPdf = pdfGenerationService.generateUnemploymentCertificate( name );

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper( message, true );

        helper.setFrom( fromEmail );
        helper.setTo( toEmail );
        helper.setSubject( "Certificat de travail et attestation de chômage" );
        helper.setText( "Bonjour " + name + ",\n\nVous trouverez ci-joint votre certificat de travail et votre attestation de chômage.\n\nCordialement," );

        ByteArrayResource certResource = new ByteArrayResource( certPdf.toByteArray() );
        helper.addAttachment( "certificat_travail_" + name + ".pdf", certResource );

        ByteArrayResource unemploymentResource = new ByteArrayResource( unemploymentPdf.toByteArray() );
        helper.addAttachment( "attestation_chomage_" + name + ".pdf", unemploymentResource );

        javaMailSender.send( message );
    }
}
