package mg.itu.rh.dto;

import lombok.Data;
import mg.itu.rh.entity.CompetenceAnnonce;
import mg.itu.rh.entity.ExperiencePoste;

import java.time.LocalDate;
import java.util.List;
@Data
public class AnnonceDTO {

    private Long idPoste;
    private LocalDate dateAnnonce;
    private LocalDate dateExpiration;
    private LocalDate dateRupture;

    private List<CompetenceAnnonce> competences;
    private List<ExperiencePoste> experiences;
}
