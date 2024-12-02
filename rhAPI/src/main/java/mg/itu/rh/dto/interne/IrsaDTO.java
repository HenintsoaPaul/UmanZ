package mg.itu.rh.dto.interne;

import lombok.Data;

@Data
public class IrsaDTO {
    public static double minimum = 50000;

    double inferieur;
    double superieur;
    double taux;
    double montant;

    public IrsaDTO(double inferieur, double superieur, double taux) {
        this.inferieur = inferieur;
        this.superieur = superieur;
        this.taux = taux;
    }

    public IrsaDTO(){}

    public static double getTotalIrsaPayer(IrsaDTO[] tabIrsa){
        double total=0;
        for (int i = 0; i < tabIrsa.length; i++) {
            total+=tabIrsa[i].getMontant();
        }
        double aPayer = total;
        if (total < IrsaDTO.minimum) {
            aPayer = IrsaDTO.minimum;
        }
        return aPayer;
    }
}

