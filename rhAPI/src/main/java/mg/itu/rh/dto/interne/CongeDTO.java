package mg.itu.rh.dto.interne;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CongeDTO {
    Long idContrat;
    int nbJour;
    LocalDate dateDebut;
    String motif;
    Long idTypeConge;

    // todo: add justificatif DTO
}
