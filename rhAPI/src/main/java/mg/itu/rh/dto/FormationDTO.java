package mg.itu.rh.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class FormationDTO {
    private String nomFormation;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    private List<CompetenceCibleDTO> competences;
}
