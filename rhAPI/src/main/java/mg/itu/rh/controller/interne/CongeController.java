package mg.itu.rh.controller.interne;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.rh.dto.interne.CongeDTO;
import mg.itu.rh.dto.interne.CongeTalentDTO;
import mg.itu.rh.dto.interne.PendingCongeDTO;
import mg.itu.rh.entity.interne.Conge;
import mg.itu.rh.other.POV;
import mg.itu.rh.repository.interne.CongeRepository;
import mg.itu.rh.service.interne.CongeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/conges" )
public class CongeController {
    private final CongeRepository congeRepository;
    private final CongeService congeService;

    public CongeController( CongeRepository congeRepository, CongeService congeService ) {
        this.congeRepository = congeRepository;
        this.congeService = congeService;
    }

    @GetMapping
    @JsonView( POV.Conge.class )
    public List<Conge> findAll() {
        return congeRepository.findAll();
    }

    @GetMapping( "/validated" )
    @JsonView( POV.Full.class )
    public List<CongeTalentDTO> findAllValide() {
        return congeService.findAllValide();
    }

    @GetMapping( "/refused" )
    @JsonView( POV.Full.class )
    public List<CongeTalentDTO> findALlNonValide() {
        return congeService.findAllNonValide();
    }

    @GetMapping( "/talent/{id}" )
    @JsonView( POV.Public.class )
    public List<Conge> getCongeByTalent( @PathVariable( "id" ) Long id ) {
        return congeService.findCongeByIdTalent( id );
    }

//    /**
//     * Retourne le solde de conge paye pour un individu identifie par son contrat.
//     * Cette fonction suppose que l' idContrat est pour un contrat qui est toujours
//     * en cours jusqu'a present.
//     *
//     * @param idContrat id du contrat d'un individu
//     */
//    @GetMapping( "/{id}/payes/nb_jour" )
//    @JsonView( POV.Conge.class )
//    public SoldeCongeDTO getSoldeCongePayeByContrat( @PathVariable( "id" ) Long idContrat ) {
//        return congeService.getSoldeCongePayeByIdContrat( idContrat );
//    }

    @JsonView( POV.Public.class )
    @PostMapping( "/demandes" )
    public Conge saveDemande( @RequestBody CongeDTO congeDTO ) {
        return congeService.saveDemandeConge( congeDTO );
    }

    @GetMapping( "/needs-validation" )
    @JsonView( POV.Conge.class )
    public List<PendingCongeDTO> findAllCongeNeedsValidation() {
        return congeService.findAllCongeNeedsValidation();
    }

    @PutMapping( "/validate/{idConge}" )
    @JsonView( POV.Conge.class )
    public Conge validate( @PathVariable( "idConge" ) Long idConge ) {
        return congeService.validate( idConge );
    }

    @PutMapping( "/refuse/{idConge}" )
    @JsonView( POV.Conge.class )
    public Conge refuse( @PathVariable( "idConge" ) Long idConge, @RequestBody String motifRefus ) {
        return congeService.refuse( idConge, motifRefus );
    }
}
