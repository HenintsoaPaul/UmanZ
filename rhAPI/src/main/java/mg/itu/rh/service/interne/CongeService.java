package mg.itu.rh.service.interne;

import mg.itu.rh.constante.ConstanteConge;
import mg.itu.rh.dto.interne.CongeDTO;
import mg.itu.rh.dto.interne.CongeTalentDTO;
import mg.itu.rh.dto.interne.PendingCongeDTO;
import mg.itu.rh.dto.interne.SoldeCongeDTO;
import mg.itu.rh.entity.interne.Conge;
import mg.itu.rh.entity.interne.Contrat;
import mg.itu.rh.exception.interne.CongeException;
import mg.itu.rh.repository.interne.CongeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class CongeService {
    private final CongeRepository congeRepository;
    private final ContratService contratService;
    private final TypeCongeService typeCongeService;

    public CongeService( CongeRepository congeRepository, ContratService contratService, TypeCongeService typeCongeService ) {
        this.congeRepository = congeRepository;
        this.contratService = contratService;
        this.typeCongeService = typeCongeService;
    }

    public Conge findById( Long idConge ) {
        return congeRepository.findById( idConge )
                .orElseThrow( () -> new RuntimeException( "Conge not found" ) );
    }

    public List<PendingCongeDTO> findAllCongeNeedsValidation() {
        List<PendingCongeDTO> pendingConges = new ArrayList<>();
        double solde;
        for ( Conge conge : this.congeRepository.findAllCongeNeedsValidation() ) {
            if ( conge.getTypeConge().getIdTypeConge() == 1L ) {
                solde = getSoldeCongePayeByContrat( conge.getContrat() ).getSolde();
            } else if ( conge.getTypeConge().getIdTypeConge() == 3L ) {
                solde = getSoldeCongeExceptionnelByContrat( conge.getContrat(), conge.getDateDebut().getYear() );
            } else solde = -1;
            PendingCongeDTO dto = new PendingCongeDTO( conge, solde );
            pendingConges.add( dto );
        }
        return pendingConges;
    }

    public List<Conge> findAllValidatedByIdContrat( Long idContrat ) {
        return congeRepository.findAllValidatedByIdContrat( idContrat );
    }

    public List<CongeTalentDTO> findAllValide() {
        return congeRepository.findAllWithTalent();
    }

    public Conge validate( Long idConge ) {
        Conge conge = this.findById( idConge );
        conge.setDateValidation( LocalDate.now() );
        return congeRepository.save( conge );
    }

    public Conge refuse( Long idConge, String motifRefus ) {
        Conge conge = this.findById( idConge );
        conge.setDateRefus( LocalDate.now() );
        conge.setMotifRefus( motifRefus );
        return congeRepository.save( conge );
    }

    public List<CongeTalentDTO> findAllNonValide() {
        return congeRepository.findAllWithTalentNonValide();
    }

    public List<Conge> findCongeByIdTalent( Long idTalent ) {
        return congeRepository.findCongeByIdTalent( idTalent );
    }

    public Conge saveDemandeConge( CongeDTO congeDTO ) {
        Contrat contrat = contratService.findById( congeDTO.getIdContrat() );
        Conge conge = new Conge();
        conge.setContrat( contrat );
        conge.setDateDebut( congeDTO.getDateDebut() );
        conge.setMotif( congeDTO.getMotif() );
        conge.setNbJour( congeDTO.getNbJour() );
        conge.setTypeConge( this.typeCongeService.findById( congeDTO.getIdTypeConge() ) );

//        Decommenter si on a besoin de controller le solde avant une demande
//        Long idType = congeDTO.getIdTypeConge();
//        if ( idType == 1L ) {
//            this.controllerSoldeCongePaye( conge );
//        } else if ( idType == 3L ) {
//            this.controllerSoldeCongeExceptionnel( conge, congeDTO.getDateDebut().getYear() );
//        }

        return congeRepository.save( conge );
    }

    // Get nbPris
    public int getNbCongePayePrisByIdContrat( Long idContrat ) {
        return this.congeRepository.findNbJourCongePayePris( idContrat );
    }

    public int getNbCongeExceptionnelPrisByIdContrat( Long idContrat, int year ) {
        return this.congeRepository.findNbJourCongeExceptionnelPris( idContrat, year );
    }

    // Get solde

    /**
     * Olona efa niasa 12 mois izay vao mahazo maka conge, fa misy cumul fona pendant ce temps.
     *
     * @return nbMoisService * 2.5 - nbEfaPris
     */
    private double getSoldeCongePaye( long nbMoisDeService, int nbJourPris ) {
        double nbJourCongeRehetra = ConstanteConge.nbCongePayeMensuel * nbMoisDeService;
        return nbJourCongeRehetra - nbJourPris;
    }

    public SoldeCongeDTO getSoldeCongePayeByContrat( Contrat contrat ) {
        Long idContrat = contrat.getIdContrat();
        int nbCongePayePris = this.getNbCongePayePrisByIdContrat( idContrat );

        long nbMoisDeService = ChronoUnit.MONTHS.between( contrat.getDateDebut(), LocalDate.now() );
        double solde = this.getSoldeCongePaye( nbMoisDeService, nbCongePayePris );

        return new SoldeCongeDTO( idContrat, nbCongePayePris, solde, nbMoisDeService );
    }

    private double getSoldeCongeExceptionnelByContrat( Contrat contrat, int year ) {
        double nbPrisOnYear = this.getNbCongeExceptionnelPrisByIdContrat( contrat.getIdContrat(), year );
        return ConstanteConge.nbCongeExceptionnelAnnuel - nbPrisOnYear;
    }

    // Controls
    private void controller12moisServiceMin( long monthDifference )
            throws CongeException {
        if ( monthDifference < 12 ) throw new CongeException( monthDifference );
    }

    private void controllerSoldeCongePaye( Conge conge )
            throws RuntimeException, CongeException {
        Contrat contrat = conge.getContrat();
        long nbMoisDeService = ChronoUnit.MONTHS.between( contrat.getDateDebut(), conge.getDateDebut() );
        controller12moisServiceMin( nbMoisDeService );

        int nbPris = this.getNbCongePayePrisByIdContrat( contrat.getIdContrat() );
        double solde = getSoldeCongePaye( nbMoisDeService, nbPris );

        SoldeCongeDTO soldeCongeDTO = new SoldeCongeDTO( contrat.getIdContrat(), nbPris, solde, nbMoisDeService );
        soldeCongeDTO.controllerDemande( conge.getNbJour() );

        // TODO: controller justificatif pour les conges maladies
        System.out.println( "Manao check justificatif maladie..." );
        // ...
    }

    private void controllerSoldeCongeExceptionnel( Conge conge, int year )
            throws CongeException {
        Contrat contrat = conge.getContrat();

        int nbPris = this.getNbCongeExceptionnelPrisByIdContrat( contrat.getIdContrat(), year );
        double solde = getSoldeCongeExceptionnelByContrat( contrat, year );

        SoldeCongeDTO soldeCongeDTO = new SoldeCongeDTO( contrat.getIdContrat(), nbPris, solde, year );
        soldeCongeDTO.controllerDemande( conge.getNbJour() );
    }
}
