package mg.itu.rh.service.interne;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mg.itu.rh.entity.interne.HeureSupplementaire;
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
    private final HeureSupplementaireRepository heureSupplementaireRepository;
    private final JourFerieService jourFerieService;
    private final ContratService contratService;

    @Transactional
    public void save(HeureSupplementaireRequest heureSupplementaireRequest) {
        LocalDateTime dateHeureDebut = heureSupplementaireRequest.getDateHeureDebut();
        double nbHeure = heureSupplementaireRequest.getNbHeure();
        double tauxMajoration = getTauxMajoration(dateHeureDebut, nbHeure, jourFerieService.getAllForYear(dateHeureDebut.getYear()));

        HeureSupplementaire heureSupplementaire = HeureSupplementaire.builder()
            .motif(heureSupplementaireRequest.getMotif())
            .dateHeureDebut(dateHeureDebut)
            .nbHeure(nbHeure)
            .tauxMajoration(tauxMajoration)
            .contrat(contratService.findById(heureSupplementaireRequest.getIdContrat()))
            .build();

        heureSupplementaireRepository.save(heureSupplementaire);
    }

    private double getTauxMajoration(
        LocalDateTime dateHeureDebut, double nbHeure, List<LocalDate> joursFeries
    ) {
        double taux = 0.0;

        if (dateHeureDebut.getDayOfWeek() == DayOfWeek.SUNDAY)  taux = 1.4;
        if (joursFeries.contains(dateHeureDebut.toLocalDate())) taux = 2;

        if (taux == 0) {
            if (nbHeure <= 8)
                 taux = 1.3;
            else taux = 1.5;
        }

        return taux;
    }
}
