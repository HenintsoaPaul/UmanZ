package mg.itu.rh.dto;

import java.util.List;

public class TalentDTO {
    private String nom;
    private String prenom;
    private String mail;
    private String password;
    private List<Long> idCompetences;
    private List<ExperienceTalentDTO> experiences;
}
