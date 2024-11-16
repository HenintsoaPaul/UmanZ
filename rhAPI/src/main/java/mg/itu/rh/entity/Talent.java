package mg.itu.rh.entity;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.other.POV;

@Entity
@Data
public class Talent {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id_talent" )
    @JsonView({POV.Public.class,POV.Question.class})
    private Long idTalent;

    @JsonView({POV.Public.class,POV.Question.class})
    private String nom;

    @JsonView({POV.Public.class,POV.Question.class})
    private String prenom;

    @JsonView( { POV.Public.class } )
    private String mail;

    @JsonView( { POV.Public.class } )
    private String password;

    @JsonView( { POV.Public.class } )
    @Column( name = "is_admin" )
    private boolean isAdmin;

    @JsonView({POV.Public.class})
    @OneToMany(mappedBy="talent",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<TalentCompetence> talentCompetences;

    @JsonView({POV.Public.class})
    @OneToMany(mappedBy="talent",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<ExperienceTalent> experienceTalents;

    @ManyToMany
    @JoinTable(
            name = "talent_diplome",
            joinColumns = @JoinColumn(name = "id_talent"),
            inverseJoinColumns = @JoinColumn(name = "id_diplome")
    )
    @JsonView({POV.Public.class})
    private Set<Diplome> diplomes;

    @JsonView( { POV.Public.class } )
    @OneToMany( mappedBy = "talent", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private Set<CompetenceTalent> competenceTalents;

    @JsonView({POV.Public.class})
    @OneToMany(mappedBy="talent",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<TalentLangue> talentLangues;
}
