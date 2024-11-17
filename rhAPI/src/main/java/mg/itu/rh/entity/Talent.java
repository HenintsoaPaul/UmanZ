package mg.itu.rh.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.other.POV;

@Entity
@Data
public class Talent {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_talent")
    @JsonView({POV.Public.class})
    private Long idTalent;

    @Column(name="nom")
    @JsonView({POV.Public.class})
    private String nom;

    @Column(name="prenom")
    @JsonView({POV.Public.class})
    private String prenom;

    @Column(name="mail")
    @JsonView({POV.Public.class})
    private String mail;

    @Column(name="password")
    @JsonView({POV.Public.class})
    private String password;

    @JsonView({POV.Public.class})
    @Column(name = "is_admin")
    private boolean isAdmin;

    @OneToMany(mappedBy = "talent", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonView({POV.Public.class})
    private List<TalentCompetence> talentsCompetences = new ArrayList<>();

    @OneToMany(mappedBy = "talent")
    @JsonView({POV.Public.class})
    private List<TalentDiplome> talentsDiplomes = new ArrayList<>();

    @OneToMany(mappedBy = "talent")
    @JsonView({POV.Public.class})
    private List<Contrat> contrats = new ArrayList<>();

    private int scoreTotal;
    
    public String getEmail(){
        return this.mail;
    }

    public Long getId(){
        return this.idTalent;
    }

    public int getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(int scoreTotal) {
        this.scoreTotal = scoreTotal;
    }
}