package mg.itu.rh.dto.talent;

import lombok.Data;
import mg.itu.rh.dto.critere.DiplomeDTO;
import mg.itu.rh.dto.critere.LangueDTO;

import java.util.List;

@Data
public class TalentDTO {
    private String nom;
    private String prenom;
    private String mail;
    private String password;
    private boolean isAdmin;

    private List<CompetenceTalentDTO> competences;
    private List<ExperienceTalentDTO> experiences;

    private List<DiplomeDTO> diplomes;
    private List<LangueDTO> langues;
}
