package mg.itu.rh.dto.interne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IrsaDTO {
    public static double minimum = 50000;

    double inferieur;
    double superieur;
    double taux;
    double montant;

    public IrsaDTO( double inferieur, double superieur, double taux ) {
        this.inferieur = inferieur;
        this.superieur = superieur;
        this.taux = taux;
    }

    public static double getTotalIrsaPayer( IrsaDTO[] tabIrsa ) {
        double total = 0;
        for ( IrsaDTO irsaDTO : tabIrsa ) {
            total += irsaDTO.getMontant();
        }
        return Math.max( total, IrsaDTO.minimum );
    }
}

