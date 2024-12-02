package mg.itu.rh.controller.interne;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import mg.itu.rh.constante.ConstanteEmail;
import mg.itu.rh.dto.interne.RuptureDTO;
import mg.itu.rh.dto.rupture.DemissionRequest;
import mg.itu.rh.dto.rupture.RenvoiRequest;
import mg.itu.rh.dto.rupture.RetraiteRequest;
import mg.itu.rh.entity.interne.Rupture;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.interne.EmailService;
import mg.itu.rh.service.interne.RuptureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping( "/ruptures" )
public class RuptureController {
    private final EmailService emailService;
    private final RuptureService ruptureService;

    @PostMapping( "/demission" )
    public ResponseEntity<String> demission( @RequestBody DemissionRequest demissionRequest ) {
        try {
            ruptureService.saveDemission( demissionRequest );

            String toEmail = ConstanteEmail.mailRh;
            emailService.writeEmail( toEmail, demissionRequest.getSubject(), demissionRequest );
            return ResponseEntity.ok( "Email envoyé avec succès." );
        } catch ( Exception e ) {
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR )
                    .body( "Erreur lors de l'envoi de l'email : " + e.getMessage() );
        }
    }

    @PostMapping( "/retraite" )
    public ResponseEntity<String> retraite( @RequestBody RetraiteRequest retraiteRequest ) {
        try {
            ruptureService.saveRetraite( retraiteRequest );

            String toEmail = ConstanteEmail.mailRh;
            emailService.writeEmail( toEmail, retraiteRequest.getSubject(), retraiteRequest );
            return ResponseEntity.ok( "Email envoyé avec succès." );
        } catch ( Exception e ) {
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR )
                    .body( "Erreur lors de l'envoi de l'email : " + e.getMessage() );
        }
    }

    @PostMapping( "/renvoi" )
    public ResponseEntity<String> renvoi( @RequestBody RenvoiRequest renvoiRequest ) {
        try {
            ruptureService.saveRenvoi( renvoiRequest );

            emailService.writeEmail( renvoiRequest.getEmail(), renvoiRequest.getSubject(), renvoiRequest );
            return ResponseEntity.ok( "Email envoyé avec succès." );
        } catch ( Exception e ) {
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR )
                    .body( "Erreur lors de l'envoi de l'email : " + e.getMessage() );
        }
    }

    @PostMapping( "/sendDossier" )
    @JsonView( POV.Public.class )
    public ResponseEntity<Map<String, String>> sendEmailDossier( @RequestBody Map<String, String> requestData ) {
        Map<String, String> response = new HashMap<>();
        String email = requestData.get( "email" );
        String name = requestData.get( "name" );

        if ( email == null || name == null ) {
            response.put( "message", "Paramètre manquant" );
            return ResponseEntity.badRequest().body( response );
        }

        try {
            emailService.sendEmailWithAttachments( email, name );
            response.put( "message", "Email envoyé avec succès à " + email );
            return ResponseEntity.ok( response );
        } catch ( Exception e ) {
            response.put( "message", "Erreur lors de l'envoi de l'email: " + e.getMessage() );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( response );
        }
    }

    @PostMapping
    @JsonView( POV.Public.class )
    public Rupture save( @RequestBody RuptureDTO ruptureDTO )
            throws Exception {
        return ruptureService.save( ruptureDTO );
    }

    @GetMapping( "/pendings" )
    @JsonView( POV.Public.class )
    public List<Rupture> findAllPendingRuptures() {
        return ruptureService.findAllPendingRuptures();
    }

    @GetMapping( "/pendings/{idContrat}" )
    @JsonView( POV.Public.class )
    public Rupture findAllPendingRupturesOfEmp( @PathVariable Long idContrat ) {
        return ruptureService.findAllPendingRupturesOfEmp( idContrat );
    }
}
