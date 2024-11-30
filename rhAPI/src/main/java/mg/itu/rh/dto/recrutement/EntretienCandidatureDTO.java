package mg.itu.rh.dto.recrutement;

import lombok.Data;

import java.time.LocalDate;
@Data
public class EntretienCandidatureDTO {
    private Long idEntretien;
    private Long idTalent;
    private Long idAnnonce;
    private LocalDate dateCreation;
    private LocalDate dateValidation;
    private int note;
    private String motif;
    private int niveau;
}
