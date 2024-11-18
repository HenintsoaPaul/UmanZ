package mg.itu.rh.dto;

import lombok.Data;

@Data
public class EntretienValidationDTO {
    private Long idTalent;
    private Long idAnnonce;
    private Long idEntretien;
    private int note;
    private String motif;
}
