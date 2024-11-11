package mg.itu.rh.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class AnnonceDTO {
    
    private Long idPoste;
    private List<Long> idExperiencePostes;
    private LocalDate dateAnnonce;
    private LocalDate dateExpiration;
    private LocalDate dateRupture;
    private List<Long> idCompetences;

}