package mg.itu.rh.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AnnonceDTO {
    private Long idPoste;
    private LocalDate dateAnnonce;
    private LocalDate dateExpiration;

    private List<CompetenceAnnonceDTO> competences;
    private List<ExperiencePosteDTO> experiences;
}
