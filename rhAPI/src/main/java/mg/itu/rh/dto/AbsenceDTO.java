package mg.itu.rh.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AbsenceDTO {
    private String motif;
    private LocalDate dateAbsence;
    private Long idContrat;
}
