package mg.itu.rh.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EntretienValidationDTO {
    private Long idTalent;
    private Long idAnnonce;
    private Long idEntretien;
    private Long note;
}
