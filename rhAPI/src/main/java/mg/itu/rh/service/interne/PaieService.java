package mg.itu.rh.service.interne;

import jakarta.transaction.Transactional;
import mg.itu.rh.dto.interne.DetailsFichePaieBruteDTO;
import mg.itu.rh.dto.interne.FicheDTO;
import mg.itu.rh.entity.interne.Contrat;
import mg.itu.rh.entity.talent.Talent;
import mg.itu.rh.repository.interne.ContratRepository;
import mg.itu.rh.service.talent.TalentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaieService {

    private final ContratRepository contratRepository;
    private final TalentService talentService;
    private final AbsenceService absenceService;

    public PaieService(ContratRepository contratRepository, TalentService talentService, AbsenceService absenceService) {
        this.contratRepository = contratRepository;
        this.talentService = talentService;
        this.absenceService = absenceService;
    }

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
        int tauxJournalier = (int)(contratActuel.getSalaireHoraire()*nbHeure);
        double tauxMensuel = contratActuel.getSalaireHoraire()*nbHeureMois;

        details.add(getSalaireBaseDetails(tauxJournalier, tauxMensuel));
        details.add(getAbsenceDeductibleDetails(contratActuel.getIdContrat(), mois, annee, tauxJournalier));

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
}
