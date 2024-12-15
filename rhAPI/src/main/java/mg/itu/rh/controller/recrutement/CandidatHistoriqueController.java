package mg.itu.rh.controller.recrutement;

import mg.itu.rh.entity.talent.Talent;
import mg.itu.rh.other.POV;
import mg.itu.rh.entity.recrutement.CandidatHistorique;
import mg.itu.rh.repository.talent.TalentRepository;
import mg.itu.rh.service.recrutement.CandidatHistoriqueService;
import mg.itu.rh.service.talent.TalentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( "candidats" )
public class CandidatHistoriqueController {
    private final TalentService talentService;
    private final TalentRepository talentRepository;
    private final CandidatHistoriqueService candidatHistoriqueService;

    public CandidatHistoriqueController( TalentService talentService, TalentRepository talentRepository, CandidatHistoriqueService candidatHistoriqueService ) {
        this.talentService = talentService;
        this.talentRepository = talentRepository;
        this.candidatHistoriqueService = candidatHistoriqueService;
    }

    @PutMapping( "/{id}/actions" )
    @JsonView( POV.Public.class )
    public ResponseEntity<String> effectuerAction(
            @PathVariable Long id,
            @RequestBody Map<String, String> details ) {
        Optional<Talent> candidatOpt = talentRepository.findById( id );
        if ( candidatOpt.isPresent() ) {
            Talent candidat = candidatOpt.orElse( null );
            String email = candidat.getMail();
            String action = details.get( "action" );
            if ( "entretient".equals( action ) ) {
                talentService.prendreEntretien( email );
            } else {
                talentService.rejetCandidat( email );
            }
            return ResponseEntity.ok( "Action effectuée et enregistrée." );
        }
        return ResponseEntity.status( 404 ).body( "Candidat non trouvé." );
    }

    @GetMapping( "/talent/{idTalent}" )
    @JsonView( POV.Public.class )
    public List<CandidatHistorique> getCandidatsByTalentId( @PathVariable Long idTalent ) {
        return candidatHistoriqueService.getByTalentId( idTalent );
    }

    @GetMapping( "/talent/historique" )
    @JsonView( POV.Public.class )
    public List<CandidatHistorique> getAllCandidatsHistorique() {
        List<Talent> candidats = talentService.getAll();
        List<CandidatHistorique> historiques = new ArrayList<>();
        for ( Talent candidat : candidats ) {
            List<CandidatHistorique> historique = candidatHistoriqueService.getByTalentId( candidat.getIdTalent() );
            historiques.addAll( historique );
        }
        return historiques;
    }
}

