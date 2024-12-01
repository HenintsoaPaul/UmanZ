package mg.itu.rh.dto.talent;

import lombok.Data;
import mg.itu.rh.dto.critere.DiplomeDTO;
import mg.itu.rh.dto.critere.LangueDTO;

import java.time.LocalDate;
import java.util.List;

@Data
public class TalentDTO {
    private String nom;
    private String prenom;
    private String mail;
    private String password;
    private boolean isAdmin;
    private LocalDate dateNaissance;
    private String idCnaps;

    private List<CompetenceTalentDTO> competences;
    private List<ExperienceTalentDTO> experiences;

    private List<DiplomeDTO> diplomes;
    private List<LangueDTO> langues;
}
