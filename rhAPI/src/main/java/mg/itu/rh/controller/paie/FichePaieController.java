package mg.itu.rh.controller.paie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import mg.itu.rh.dto.paie.Irsa;
import mg.itu.rh.service.paie.FichePaieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class FichePaieController {
    @Autowired
    FichePaieService fpService;

    @GetMapping("/test/irsa")
    public String testIrsa(){
        double imposable = 4022575;
        Irsa[] tabIrsa = fpService.calculateIrsa(imposable);
        System.out.println("La liste des irsa");
        for (int i = 0; i < tabIrsa.length; i++) {
            System.out.println(tabIrsa[i]);
        }
        System.out.println("La totale = "+Irsa.getTotalIrsaPayer(tabIrsa));
        return "Done";
    }
}
