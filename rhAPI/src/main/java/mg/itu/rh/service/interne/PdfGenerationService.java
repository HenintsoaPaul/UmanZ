package mg.itu.rh.service.interne;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfGenerationService {

    public ByteArrayOutputStream generateCertificate( String name )
            throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfWriter.getInstance( document, outputStream );

        document.open();

        document.add( new Paragraph( "CERTIFICAT DE TRAVAIL" ) );
        document.add( new Paragraph( "Nous, soussigné, attestons par la présente que :" ) );
        document.add( new Paragraph( "Nom: " + name ) );
        document.add( new Paragraph( "A travaillé au sein de notre entreprise du..." ) );
        document.add( new Paragraph( "Nous lui délivrons ce certificat pour toutes fins utiles." ) );
        document.add( new Paragraph( "Fait à [Ville], le [Date]." ) );
        document.add( new Paragraph( "Signature de l'employeur" ) );

        document.close();

        return outputStream;
    }

    public ByteArrayOutputStream generateUnemploymentCertificate( String name )
            throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfWriter.getInstance( document, outputStream );

        document.open();

        document.add( new Paragraph( "ATTESTATION DE CHOMAGE" ) );
        document.add( new Paragraph( "Nous, soussigné, attestons que :" ) );
        document.add( new Paragraph( "Nom: " + name ) );
        document.add( new Paragraph( "A été licencié pour cause de rupture de contrat de travail." ) );
        document.add( new Paragraph( "Cette attestation est délivrée pour toute utilisation qui pourrait en être faite." ) );
        document.add( new Paragraph( "Fait à [Ville], le [Date]." ) );
        document.add( new Paragraph( "Signature de l'employeur" ) );

        document.close();

        return outputStream;
    }
}
