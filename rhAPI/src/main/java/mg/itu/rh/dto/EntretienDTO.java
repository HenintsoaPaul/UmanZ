package mg.itu.rh.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class EntretienDTO {
    private Long idEntretien;
    private LocalDate dateEntretien;
    private int note;
    private String motif;
}
