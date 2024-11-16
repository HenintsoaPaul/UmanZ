package mg.itu.rh.dto;

import lombok.Data;

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
}
