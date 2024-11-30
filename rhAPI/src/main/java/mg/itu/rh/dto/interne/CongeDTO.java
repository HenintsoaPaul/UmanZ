package mg.itu.rh.dto.interne;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CongeDTO {
    Long idTalent;
    int nbJour;
    LocalDate dateDebut;
    String motif;
}
