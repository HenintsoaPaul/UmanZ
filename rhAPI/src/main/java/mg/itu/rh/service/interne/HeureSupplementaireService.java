package mg.itu.rh.service.interne;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mg.itu.rh.dto.interne.HeureSupplementaireDTO;
import mg.itu.rh.entity.interne.Contrat;
import mg.itu.rh.entity.interne.HeureSupplementaire;
import mg.itu.rh.exception.MaxHeuresSuppDepasseException;
import mg.itu.rh.repository.interne.HeureSupplementaireRepository;
import mg.itu.rh.request.HeureSupplementaireRequest;
import mg.itu.rh.service.api.JourFerieService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class HeureSupplementaireService {
    public static final short MAX_HEURE_SUPP_HEBDOMADAIRE = 20;

    private final HeureSupplementaireRepository heureSupplementaireRepository;
    private final JourFerieService jourFerieService;
    private final ContratService contratService;

    public List<HeureSupplementaireDTO> getByContratAndMoisAndAnnee(Long idContrat, int mois, int annee) {
        return heureSupplementaireRepository.findSumHeuresAndMontantByContratAndMoisAndAnnee(idContrat, mois, annee);
    }

    @Transactional
    public Double getTotalHeuresForWeekByContrat( Long idContrat, LocalDateTime dateHeureDebut ) {
        return heureSupplementaireRepository.findTotalHeuresForWeekByContrat( idContrat, dateHeureDebut );
    }

    @Transactional
    public void save( HeureSupplementaireRequest heureSupplementaireRequest )
            throws MaxHeuresSuppDepasseException {
        LocalDateTime dateHeureDebut = heureSupplementaireRequest.getDateHeureDebut();


        Contrat contrat = contratService.findById( heureSupplementaireRequest.getIdContrat() );
        Double totalHeuresSuppForWeek = heureSupplementaireRepository.findTotalHeuresForWeekByContrat( contrat.getIdContrat(), dateHeureDebut );
        if ( totalHeuresSuppForWeek >= MAX_HEURE_SUPP_HEBDOMADAIRE )
            throw new RuntimeException("Maximum d'heures supp atteint");

        double nbHeure = heureSupplementaireRequest.getNbHeure();
        double excedant = totalHeuresSuppForWeek + nbHeure - MAX_HEURE_SUPP_HEBDOMADAIRE;

        if ( dateHeureDebut.isAfter( LocalDateTime.now() ) && excedant > 0 )
            throw new MaxHeuresSuppDepasseException( totalHeuresSuppForWeek, excedant );
        if ( excedant > 0 ) nbHeure = MAX_HEURE_SUPP_HEBDOMADAIRE - totalHeuresSuppForWeek;

        HeureSupplementaire heureSupplementaire = HeureSupplementaire.builder()
                .motif( heureSupplementaireRequest.getMotif() )
                .dateHeureDebut( dateHeureDebut )
                .dateHeureCreation(LocalDateTime.now())
                .nbHeure( nbHeure )
                .tauxMajoration( getTauxMajoration( dateHeureDebut, totalHeuresSuppForWeek,
                        jourFerieService.getAllForYear( dateHeureDebut.getYear() ) ) )
                .contrat( contrat )
                .build();

        heureSupplementaireRepository.save( heureSupplementaire );
    }

    private double getTauxMajoration(
            LocalDateTime dateHeureDebut, double totalHeuresSuppForWeekActuel, List<LocalDate> joursFeries
    ) {
        double taux = 0.0;

        if ( dateHeureDebut.getDayOfWeek() == DayOfWeek.SUNDAY ) taux = 1.4;
        if ( joursFeries.contains( dateHeureDebut.toLocalDate() ) ) taux = 2;

        if ( taux == 0 ) {
            if ( totalHeuresSuppForWeekActuel <= 8 )
                taux = 1.3;
            else taux = 1.5;
        }

        return taux;
    }
}
