package mg.itu.rh.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import jakarta.persistence.*;
import mg.itu.rh.other.POV;

import java.util.*;

@Entity
@Data
public class Competence {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_competence")
    @JsonView({POV.Public.class})
    private Long idCompetence;

    @Column(name="nom_competence")
    @JsonView({POV.Public.class})
    private String nomCompetence;

    @OneToMany(mappedBy = "competence", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonView({POV.Public.class})
    private List<TalentCompetence> talentCompetences;

    public Competence() {}

    public Competence(Long idCompetence, String competence) {
        this.idCompetence = idCompetence;
        this.nomCompetence = competence;
    }
}
