package mg.itu.rh.service.interne;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mg.itu.rh.dto.interne.DetailsFichePaieBruteDTO;
import mg.itu.rh.dto.interne.FicheDTO;
import mg.itu.rh.dto.interne.HeureSupplementaireDTO;
import mg.itu.rh.dto.interne.IrsaDTO;
import mg.itu.rh.entity.interne.Contrat;
import mg.itu.rh.entity.talent.Talent;
import mg.itu.rh.repository.interne.ContratRepository;
import mg.itu.rh.service.talent.TalentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaieService {
    private final ContratRepository contratRepository;
    private final TalentService talentService;
    private final AbsenceService absenceService;
    private final HeureSupplementaireService heureSupplementaireService;

    private LocalDate getLastDateOfMonth( int month, int year ) {
        int nextMonth = month + 1;
        if ( nextMonth == 13 ) {
            year ++;
            nextMonth = 1;
        }
        return LocalDate.of( year, nextMonth, 1 ).minusDays( 1 );
    }

    @Transactional
    public FicheDTO findFiche( int annee, int mois, Long idTalent ) {
        return findFiche( getLastDateOfMonth( mois, annee ), idTalent );
    }

    @Transactional
    public FicheDTO findFiche( LocalDate lastDayOfMonth, Long idTalent ) {
        Talent talent = talentService.findById( idTalent );

        Contrat contratEmbauche = contratRepository.findContratEmbauche( idTalent )
                .orElseThrow( () -> new RuntimeException( "Cette personne n'a jamais travaille chez nous" ) );
        Contrat contratActuel = contratRepository.findContratByDateTalent( lastDayOfMonth, idTalent )
                .orElseThrow( () -> new RuntimeException( "Cette personne n'est pas un employe la date du " + lastDayOfMonth ) );

        return new FicheDTO( talent, contratEmbauche, contratActuel, lastDayOfMonth );
    }

    @Transactional
    public List<FicheDTO> findAllFichePaiesOnDate( int month, int year ) {
        List<FicheDTO> fichePaies = new ArrayList<>();
        LocalDate lastDayOfMonth = getLastDateOfMonth( month, year );
        List<Contrat> contratsEnCours = this.contratRepository.findAllContratEnCoursOnDate( lastDayOfMonth );

        for ( Contrat c : contratsEnCours ) {
            fichePaies.add( this.findFiche( lastDayOfMonth, c.getTalent().getIdTalent() ) );
        }
        return fichePaies;
    }

    public List<DetailsFichePaieBruteDTO> findDetailsFichePaieBrute(int annee, int mois, Long idTalent) {
        List<DetailsFichePaieBruteDTO> details = new ArrayList<>();

        double nbHeureMois=173.33;
        double nbHeure=24.0;
        LocalDate dateActuel=LocalDate.of(annee,mois+1,1).minusDays(1);
        Contrat contratActuel=contratRepository.findContratByDateTalent(dateActuel,idTalent).orElseThrow(()->new RuntimeException("Cette personne n'est pas un employe la date du "+dateActuel));
        int tauxHoraire = ( int ) contratActuel.getSalaireHoraire();
        int tauxJournalier = (int)(contratActuel.getSalaireHoraire()*nbHeure);
        double tauxMensuel = contratActuel.getSalaireHoraire()*nbHeureMois;

        details.add(getSalaireBaseDetails(tauxJournalier, tauxMensuel));
        details.add(getAbsenceDeductibleDetails(contratActuel.getIdContrat(), mois, annee, tauxJournalier));
        details.add(getPrimesRendementDetails());
        details.add(getPrimesAncienneteDetails());
        details.addAll(getHeuresSupplementairesDetails(contratActuel.getIdContrat(), mois, annee, tauxHoraire));
        details.add(getPrimesDiversesDetails());
        details.add(getRappelsDetails());
        details.add(getDroitsCongeDetails());
        details.add(getDroitsPreavisDetails());
        details.add(getIndemniteLicenciementDetails());
        details.add(getSalaireBruteDetails(details));

        return details;
    }

    private DetailsFichePaieBruteDTO getSalaireBaseDetails(int tauxJournalier, double tauxMensuel) {

        DetailsFichePaieBruteDTO salaireDetails = new DetailsFichePaieBruteDTO();
        salaireDetails.setDesignation("Salaire");
        salaireDetails.setTaux(tauxJournalier);
        salaireDetails.setMontant(tauxMensuel);
        salaireDetails.setNombre("1 mois");

        return salaireDetails;
    }

    private DetailsFichePaieBruteDTO getAbsenceDeductibleDetails(Long idContrat, int mois, int annee, int tauxJournalier) {
        DetailsFichePaieBruteDTO absenceDetails = new DetailsFichePaieBruteDTO();

        long absenceCount = absenceService.countByContratAndMoisAndAnnee(idContrat, mois, annee);
        absenceDetails.setDesignation("Absence déductible");
        absenceDetails.setTaux(tauxJournalier);
        absenceDetails.setNombre(absenceCount + " jour(s)");
        absenceDetails.setMontant(absenceCount * tauxJournalier);

        return absenceDetails;
    }

    private DetailsFichePaieBruteDTO getPrimesRendementDetails() {
        DetailsFichePaieBruteDTO primesRendementDetails = new DetailsFichePaieBruteDTO();

        primesRendementDetails.setDesignation("Primes de rendement");
        primesRendementDetails.setTaux(0);
        primesRendementDetails.setMontant(0);
        primesRendementDetails.setNombre("");

        return primesRendementDetails;
    }

    private DetailsFichePaieBruteDTO getPrimesAncienneteDetails() {
        DetailsFichePaieBruteDTO primesAncienneteDetails = new DetailsFichePaieBruteDTO();

        primesAncienneteDetails.setDesignation("Primes d'ancienneté");
        primesAncienneteDetails.setTaux(0);
        primesAncienneteDetails.setMontant(0);
        primesAncienneteDetails.setNombre("");

        return primesAncienneteDetails;
    }

    private List<DetailsFichePaieBruteDTO> getHeuresSupplementairesDetails(Long idContrat, int mois, int annee, int tauxHoraire) {
        List<DetailsFichePaieBruteDTO> heuresSupplementairesDetails = new ArrayList<>();

        List<HeureSupplementaireDTO> heureSupplementaires = heureSupplementaireService.getByContratAndMoisAndAnnee(idContrat, mois, annee);
        for (HeureSupplementaireDTO heureSupplementaire: heureSupplementaires) {
            DetailsFichePaieBruteDTO details = new DetailsFichePaieBruteDTO();
            details.setDesignation("Heure supplementaires majorés à " + heureSupplementaire.getTaux() + "%");
            details.setNombre(heureSupplementaire.getNbHeures() + " heure(s)");
            details.setTaux(tauxHoraire + (tauxHoraire * heureSupplementaire.getTaux() / 100));
            details.setMontant(details.getTaux() * heureSupplementaire.getNbHeures());
            heuresSupplementairesDetails.add(details);
        }

        return heuresSupplementairesDetails;
    }

    private DetailsFichePaieBruteDTO getPrimesDiversesDetails() {
        DetailsFichePaieBruteDTO primesDiversesDetails = new DetailsFichePaieBruteDTO();

        primesDiversesDetails.setDesignation("Primes diverses");
        primesDiversesDetails.setTaux(0);
        primesDiversesDetails.setMontant(0);
        primesDiversesDetails.setNombre("");

        return primesDiversesDetails;
    }

    private DetailsFichePaieBruteDTO getRappelsDetails() {
        DetailsFichePaieBruteDTO rappelsDetails = new DetailsFichePaieBruteDTO();

        rappelsDetails.setDesignation("Rappels sur période antérieure");
        rappelsDetails.setTaux(0);
        rappelsDetails.setMontant(0);
        rappelsDetails.setNombre("");

        return rappelsDetails;
    }

    private DetailsFichePaieBruteDTO getDroitsCongeDetails() {
        DetailsFichePaieBruteDTO droitsCongeDetails = new DetailsFichePaieBruteDTO();

        droitsCongeDetails.setDesignation("Droits de congé");
        droitsCongeDetails.setTaux(0);
        droitsCongeDetails.setMontant(0);
        droitsCongeDetails.setNombre("");

        return droitsCongeDetails;
    }

    private DetailsFichePaieBruteDTO getDroitsPreavisDetails() {
        DetailsFichePaieBruteDTO droitsPreavisDetails = new DetailsFichePaieBruteDTO();

        droitsPreavisDetails.setDesignation("Droits de préavis");
        droitsPreavisDetails.setTaux(0);
        droitsPreavisDetails.setMontant(0);
        droitsPreavisDetails.setNombre("");

        return droitsPreavisDetails;
    }

    private DetailsFichePaieBruteDTO getIndemniteLicenciementDetails() {
        DetailsFichePaieBruteDTO indemniteDetails = new DetailsFichePaieBruteDTO();

        indemniteDetails.setDesignation("Indemnité de licenciement");
        indemniteDetails.setTaux(0);
        indemniteDetails.setMontant(0);
        indemniteDetails.setNombre("");

        return indemniteDetails;
    }

    private DetailsFichePaieBruteDTO getSalaireBruteDetails(List<DetailsFichePaieBruteDTO> previousDetails) {
        DetailsFichePaieBruteDTO salaireBruteDetails = new DetailsFichePaieBruteDTO();

        double previousDetailsAmount = previousDetails.stream().mapToDouble(DetailsFichePaieBruteDTO::getMontant).sum();

        salaireBruteDetails.setDesignation("Salaire Brute");
        salaireBruteDetails.setMontant(previousDetailsAmount);

        return salaireBruteDetails;
    }

    public List<DetailsFichePaieBruteDTO> findDetailsRetenue(double salaireBrute) {
        List<DetailsFichePaieBruteDTO> details = new ArrayList<>();

        details.add(getRetenueCNaPS(salaireBrute));
        details.add(getRetenueSanitaire(salaireBrute));

        double montantImposable = salaireBrute - details.stream().mapToDouble(DetailsFichePaieBruteDTO::getMontant).sum();
        List<DetailsFichePaieBruteDTO> irsaDetails = getIrsaDTODetails(montantImposable);
        details.addAll(irsaDetails);

        DetailsFichePaieBruteDTO totalIrsaDetails = getTotalIrsaDetails(irsaDetails);
        details.add(totalIrsaDetails);

        double totalRetenue = totalIrsaDetails.getMontant();
        DetailsFichePaieBruteDTO totalRetenueDetails = getTotalRetenue(totalRetenue + details.get(0).getMontant() + details.get(1).getMontant());
        details.add(totalRetenueDetails);

        details.add(getAutresIndemnite());

        double netAPayer = salaireBrute - totalRetenueDetails.getMontant();
        details.add(getNetAPayer(netAPayer));

        return details;
    }

    private DetailsFichePaieBruteDTO getNetAPayer(double netAPayer) {
        DetailsFichePaieBruteDTO netAPayerDetails = new DetailsFichePaieBruteDTO();

        netAPayerDetails.setDesignation("Net à payer");
        netAPayerDetails.setMontant(netAPayer);
        netAPayerDetails.setSurligne(true);

        return netAPayerDetails;
    }

    private DetailsFichePaieBruteDTO getAutresIndemnite() {
        DetailsFichePaieBruteDTO autresIndemnite = new DetailsFichePaieBruteDTO();

        autresIndemnite.setDesignation("Autres indemnités");
        autresIndemnite.setMontant(0);
        autresIndemnite.setSurligne(true);

        return autresIndemnite;
    }

    private DetailsFichePaieBruteDTO getTotalRetenue(double totalRetenue) {
        DetailsFichePaieBruteDTO totalRetenueDetails = new DetailsFichePaieBruteDTO();

        totalRetenueDetails.setDesignation("Total retenue");
        totalRetenueDetails.setMontant(totalRetenue);
        totalRetenueDetails.setSurligne(true);

        return totalRetenueDetails;
    }

    private DetailsFichePaieBruteDTO getTotalIrsaDetails(List<DetailsFichePaieBruteDTO> irsaDetails) {
        DetailsFichePaieBruteDTO irsaDetail = new DetailsFichePaieBruteDTO();

        irsaDetail.setDesignation("Total IRSA");
        irsaDetail.setMontant(irsaDetails.stream().mapToDouble(DetailsFichePaieBruteDTO::getMontant).sum());
        irsaDetail.setSurligne(true);

        return irsaDetail;
    }

    public IrsaDTO[] calculateIrsaDTO(double imposable){
        IrsaDTO[] IrsaDTO = new IrsaDTO[5];
        IrsaDTO[0] = new IrsaDTO(0, 350000, 0);
        IrsaDTO[1] = new IrsaDTO(IrsaDTO[0].getSuperieur(), 400000, 0.05);
        IrsaDTO[2] = new IrsaDTO(IrsaDTO[1].getSuperieur(), 500000, 0.1);
        IrsaDTO[3] = new IrsaDTO(IrsaDTO[2].getSuperieur(), 600000, 0.15);
        IrsaDTO[4] = new IrsaDTO(IrsaDTO[3].getSuperieur(), Double.MAX_VALUE, 0.2);

        for (IrsaDTO irsaDTO : IrsaDTO) {
            if (imposable > irsaDTO.getSuperieur()) {
                irsaDTO.setMontant((irsaDTO.getSuperieur() - irsaDTO.getInferieur()) * (irsaDTO.getTaux()));
            } else {
                irsaDTO.setMontant((imposable - irsaDTO.getInferieur()) * (irsaDTO.getTaux()));
                break;
            }
        }

        return IrsaDTO;
    }

    private List<DetailsFichePaieBruteDTO> getIrsaDTODetails(double montantImposable) {
        List<DetailsFichePaieBruteDTO> details = new ArrayList<>();

        for (IrsaDTO irsa : calculateIrsaDTO(montantImposable)) {
            DetailsFichePaieBruteDTO irsaDetails = new DetailsFichePaieBruteDTO();

            irsaDetails.setDesignation("Tranche IRSA de " + irsa.getInferieur() + " à " + irsa.getSuperieur());
            irsaDetails.setTaux(irsa.getTaux() * 100);
            irsaDetails.setMontant(irsa.getMontant());

            details.add(irsaDetails);
        }

        return details;
    }

    private DetailsFichePaieBruteDTO getRetenueSanitaire(double salaireBrute) {
        DetailsFichePaieBruteDTO retenueSanitaire = new DetailsFichePaieBruteDTO();

        retenueSanitaire.setDesignation("Retenue sanitaire");
        retenueSanitaire.setMontant(salaireBrute * 1 / 100);

        return retenueSanitaire;
    }

    private DetailsFichePaieBruteDTO getRetenueCNaPS(double salaireBrute) {
        DetailsFichePaieBruteDTO retenueCNaPS = new DetailsFichePaieBruteDTO();

        double retenue = salaireBrute * 1 / 100;

        retenueCNaPS.setDesignation("Retenue CNaPS 1%");
        retenueCNaPS.setMontant(retenue > 20000 ? 20000 : retenue);

        return retenueCNaPS;
    }
}
