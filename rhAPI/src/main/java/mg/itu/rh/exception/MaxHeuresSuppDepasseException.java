package mg.itu.rh.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static mg.itu.rh.service.interne.HeureSupplementaireService.MAX_HEURE_SUPP_HEBDOMADAIRE;

@AllArgsConstructor
@Getter
public class MaxHeuresSuppDepasseException extends RuntimeException {
    private double totalHeuresSuppForWeek;
    private double excedant;

    @Override
    public String getMessage() {
        return String.format("Le total des heures supplémentaires pour la semaine est de %.2f heures, " +
                "ce qui dépasse la limite autorisée de %.2s heures. Il y a un excédent de %.2f heures",
            totalHeuresSuppForWeek, MAX_HEURE_SUPP_HEBDOMADAIRE, excedant);
    }
}
