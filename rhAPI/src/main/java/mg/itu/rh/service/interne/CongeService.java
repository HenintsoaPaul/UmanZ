package mg.itu.rh.service.interne;

import mg.itu.rh.constante.ConstanteConge;
import mg.itu.rh.dto.interne.CongeDTO;
import mg.itu.rh.dto.interne.CongeTalentDTO;
import mg.itu.rh.entity.interne.Conge;
import mg.itu.rh.entity.interne.Contrat;
import mg.itu.rh.exception.interne.CongeException;
import mg.itu.rh.repository.interne.CongeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class CongeService {
    private final CongeRepository congeRepository;
    private final ContratService contratService;

    public CongeService( CongeRepository congeRepository, ContratService contratService ) {
        this.congeRepository = congeRepository;
        this.contratService = contratService;
    }

    public Conge findById( Long idConge ) {
        return congeRepository.findById( idConge )
                .orElseThrow( () -> new RuntimeException( "Conge not found" ) );
    }

    public List<CongeTalentDTO> findAllValide() {
        return congeRepository.findAllWithTalent();
    }

    public Conge validate( Long idConge ) {
        Conge conge = this.findById( idConge );
        conge.setDateValidation( LocalDate.now() );
        return congeRepository.save( conge );
    }

    public List<CongeTalentDTO> findAllNonValide() {
        return congeRepository.findAllWithTalentNonValide();
    }

    public List<Conge> findCongeByIdTalent( Long idTalent ) {
        return congeRepository.findCongeByIdTalent( idTalent );
    }

    public Conge save( CongeDTO congeDTO )
            throws Exception {
        Contrat contrat = contratService.findById( congeDTO.getIdContrat() );
        Conge conge = new Conge();
        conge.setContrat( contrat );
        conge.setDateDebut( congeDTO.getDateDebut() );
        conge.setMotif( congeDTO.getMotif() );
        conge.setNbJour( congeDTO.getNbJour() );

        Long idType = congeDTO.getIdTypeConge();
        if ( idType == 1L ) {
            this.controllerSoldeCongePaye( conge );
        } else if ( idType == 3L ) {
            this.controllerSoldeCongeExceptionnel( conge, congeDTO.getDateDebut().getYear() );
        }
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
    private double getSoldeCongePayeByContrat( Contrat contrat )
            throws CongeException {
        long nbMoisDeService = ChronoUnit.MONTHS.between( contrat.getDateDebut(), contrat.getDateDebut() );
        controller12moisServiceMin( nbMoisDeService );

        double nbJourCongeRehetra = ConstanteConge.nbCongePayeMensuel * nbMoisDeService;
        return nbJourCongeRehetra - this.getNbCongePayePrisByIdContrat( contrat.getIdContrat() );
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

    private void controllerSolde( double solde, int nbJourDemande )
            throws CongeException {
        if ( solde - nbJourDemande <= 0 ) {
            throw new CongeException( "Solde de conge insuffisant. Il vous reste " + nbJourDemande + " jr de conge." );
        }
    }

    private void controllerSoldeCongePaye( Conge conge )
            throws RuntimeException, CongeException {
        double solde = getSoldeCongePayeByContrat( conge.getContrat() );
        controllerSolde( solde, conge.getNbJour() );

        // TODO: controller justificatif pour les conges maladies
        System.out.println( "Manao check justificatif maladie..." );
        // ...
    }

    private void controllerSoldeCongeExceptionnel( Conge conge, int year )
            throws CongeException {
        double solde = getSoldeCongeExceptionnelByContrat( conge.getContrat(), year );
        controllerSolde( solde, conge.getNbJour() );
    }
}
