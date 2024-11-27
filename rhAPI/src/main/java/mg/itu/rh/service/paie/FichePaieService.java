package mg.itu.rh.service.paie;

import org.springframework.stereotype.Service;

import mg.itu.rh.dto.paie.Irsa;

@Service
public class FichePaieService {

    public Irsa[] calculateIrsa(double imposable){
        Irsa[] irsa = new Irsa[5];
        irsa[0] = new Irsa(0, 350000, 0);
        irsa[1] = new Irsa(irsa[0].getSuperieur(), 400000, 0.05);
        irsa[2] = new Irsa(irsa[1].getSuperieur(), 500000, 0.1);
        irsa[3] = new Irsa(irsa[2].getSuperieur(), 600000, 0.15);
        irsa[4] = new Irsa(irsa[3].getSuperieur(), Double.MAX_VALUE, 0.2);

        for (int i = 0; i < irsa.length; i++) {
            if (imposable > irsa[i].getSuperieur()) {
                irsa[i].setMontant((irsa[i].getSuperieur()-irsa[i].getInferieur())*(irsa[i].getTaux()));
            }
            else{
                irsa[i].setMontant((imposable-irsa[i].getInferieur())*(irsa[i].getTaux()));
                break;
            }
        }

        return irsa;
    }
    
}
