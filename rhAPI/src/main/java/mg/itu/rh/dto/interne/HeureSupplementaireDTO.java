package mg.itu.rh.dto.interne;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class HeureSupplementaireDTO {
    private double taux;
    private double nbHeures;

    public double montant() {
        return nbHeures * taux;
    }
}
