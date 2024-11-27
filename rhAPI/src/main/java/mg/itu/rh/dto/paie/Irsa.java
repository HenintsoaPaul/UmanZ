package mg.itu.rh.dto.paie;

import lombok.Data;

@Data
public class Irsa {
    public static double minimum = 50000;

    double inferieur;
    double superieur;
    double taux;
    double montant;

    public Irsa(double inferieur, double superieur, double taux) {
        this.inferieur = inferieur;
        this.superieur = superieur;
        this.taux = taux;
    }

    public Irsa(){

    }

    public static double getTotalIrsaPayer(Irsa[] tabIrsa){
        double total=0;
        for (int i = 0; i < tabIrsa.length; i++) {
            total+=tabIrsa[i].getMontant();
        }
        double aPayer = total; 
        if (total < Irsa.minimum) {
            aPayer = Irsa.minimum;
        }
        return aPayer;
    }
}
