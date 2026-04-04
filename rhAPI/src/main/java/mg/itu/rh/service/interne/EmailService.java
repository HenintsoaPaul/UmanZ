package mg.itu.rh.service.interne;

import lombok.RequiredArgsConstructor;
import mg.itu.rh.auth.exception.MfaDeliveryException;
import mg.itu.rh.dto.rupture.RuptureRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;

import jakarta.mail.MessagingException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.MailException;

import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
        private final JavaMailSender javaMailSender;
        private final PdfGenerationService pdfGenerationService;

        @Value("${spring.mail.username}")
        private String fromEmail;

        public void writeEmail(String toEmail, String subject, RuptureRequest ruptureRequest)
                        throws MessagingException {
                String body = String.format(
                                "<h1>%s</h1>" +
                                                "<p><strong>Motif :</strong> %s</p>" +
                                                "<p><strong>Date :</strong> %s</p>",
                                subject,
                                ruptureRequest.getMotif(),
                                ruptureRequest.getDate());
                this.sendEmail(toEmail, subject, body);
        }

        public void sendEmail(String toEmail, String subject, String body)
                        throws MessagingException {
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

                helper.setFrom(fromEmail);
                helper.setTo(toEmail);
                helper.setSubject(subject);
                helper.setText(body, true);

                try {
                        javaMailSender.send(mimeMessage);
                } catch (MailException e) {
                        throw new MfaDeliveryException(e);
                }
        }

        public void sendEmailWithAttachments(String toEmail, String name)
                        throws Exception {
                ByteArrayOutputStream certPdf = pdfGenerationService.generateCertificate(name);
                ByteArrayOutputStream unemploymentPdf = pdfGenerationService.generateUnemploymentCertificate(name);

                MimeMessage message = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);

                helper.setFrom(fromEmail);
                helper.setTo(toEmail);
                helper.setSubject("Certificat de travail et attestation de chômage");
                helper.setText("Bonjour " + name
                                + ",\n\nVous trouverez ci-joint votre certificat de travail et votre attestation de chômage.\n\nCordialement,");

                ByteArrayResource certResource = new ByteArrayResource(certPdf.toByteArray());
                helper.addAttachment("certificat_travail_" + name + ".pdf", certResource);

                ByteArrayResource unemploymentResource = new ByteArrayResource(unemploymentPdf.toByteArray());
                helper.addAttachment("attestation_chomage_" + name + ".pdf", unemploymentResource);

                try {
                        javaMailSender.send(message);
                } catch (MailException e) {
                        throw new MessagingException("Service mail non disponible. Veuillez réessayer plus tard.", e);
                }
        }

        public void sendMfaCode(String toEmail, String code) throws MessagingException {
                String subject = "Votre code de vérification UmanZ";
                String body = String.format(
                                "<div style='font-family: sans-serif; padding: 20px; border: 1px solid #eee; border-radius: 10px;'>"
                                                +
                                                "<h2 style='color: #6366f1;'>Authentification à deux facteurs</h2>" +
                                                "<p>Voici votre code de vérification pour vous connecter à votre compte UmanZ :</p>"
                                                +
                                                "<div style='background: #f8fafc; padding: 15px; border-radius: 8px; font-size: 24px; font-weight: bold; text-align: center; letter-spacing: 5px; color: #1e293b;'>"
                                                +
                                                "%s" +
                                                "</div>" +
                                                "<p style='color: #64748b; font-size: 13px; margin-top: 20px;'>Si vous n'êtes pas à l'origine de cette demande, vous pouvez ignorer cet e-mail en toute sécurité.</p>"
                                                +
                                                "</div>",
                                code);
                this.sendEmail(toEmail, subject, body);
        }
}
