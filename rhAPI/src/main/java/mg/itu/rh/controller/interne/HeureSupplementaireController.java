package mg.itu.rh.controller.interne;

import mg.itu.rh.request.HeureSupplementaireRequest;
import mg.itu.rh.service.interne.HeureSupplementaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/heure_sup")
public class HeureSupplementaireController {
    private final HeureSupplementaireService heureSupplementaireService;

    public HeureSupplementaireController(HeureSupplementaireService heureSupplementaireService) {
        this.heureSupplementaireService = heureSupplementaireService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody HeureSupplementaireRequest heureSupplementaireRequest){
        try{
            heureSupplementaireService.save(heureSupplementaireRequest);
        }
        catch (Exception ex){
            return ResponseEntity.status(400).body(ex.getMessage());
        }
        return ResponseEntity.status(200).body("réussie");
    }
}
