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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_talent")
    @JsonView({POV.Public.class})
    private Long idTalent;

    @JsonView({POV.Public.class})
    private String nom;

    @JsonView({POV.Public.class})
    private String prenom;

    @JsonView({POV.Public.class})
    private String mail;

    @ManyToMany
    @JoinTable(
        name = "talent_competence",
        joinColumns = @JoinColumn(name = "id_talent"),
        inverseJoinColumns = @JoinColumn(name = "id_competence")
    )
    @JsonView({POV.Public.class})
    private Set<Competence> contacts;

    @JsonView({POV.Public.class})
    @OneToMany(mappedBy="talent",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<ExperienceTalent> experienceTalents;
}
