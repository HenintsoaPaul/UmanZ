package mg.itu.rh.dto.interne;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AbsenceDTO {
    private String motif;
    private LocalDate dateAbsence;
    private Long idTalent;
}
