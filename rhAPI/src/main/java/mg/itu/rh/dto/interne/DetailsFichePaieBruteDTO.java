package mg.itu.rh.dto.interne;

import lombok.Data;

@Data
public class DetailsFichePaieBruteDTO {

    private String designation;
    private double taux;
    private double montant;
    private String nombre;
    private boolean surligne = false;

}
