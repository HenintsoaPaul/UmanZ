package mg.itu.rh.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.itu.rh.dto.poste.PosteDTO;
import mg.itu.rh.entity.critere.Diplome;
import mg.itu.rh.entity.recrutement.CompetencePoste;
import mg.itu.rh.entity.recrutement.ExperiencePoste;
import mg.itu.rh.entity.recrutement.PosteLangue;
import mg.itu.rh.other.POV;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Poste {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_poste")
    @JsonView({POV.Public.class, POV.Conge.class})
    private Long idPoste;

    @Column(name = "nom_poste")
    @JsonView({POV.Public.class, POV.Conge.class})
    private String nomPoste;

    @Column(name="description_poste")
    @JsonView({POV.Public.class})
    private String description;

    @OneToMany(mappedBy = "poste",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonView({POV.Full.class})
    private List<CompetencePoste> competencePostes= new ArrayList<>();

    @OneToMany(mappedBy="poste",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JsonView({POV.Full.class})
    private List<ExperiencePoste> experiencePostes= new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type_poste")
    @JsonView({POV.Public.class})
    private TypePoste typePoste;

    @ManyToMany
    @JoinTable(
            name = "diplome_poste",
            joinColumns = @JoinColumn(name = "id_poste"),
            inverseJoinColumns = @JoinColumn(name = "id_diplome")
    )
    @JsonView({POV.Public.class})
    private Set<Diplome> diplomes= new HashSet<>();

    @OneToMany(mappedBy="poste",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JsonView({POV.Public.class})
    private List<PosteLangue> posteLangues= new ArrayList<>();

    @Transient
    @JsonView({POV.Public.class})
    private double pourcentage;

    public void addDiplome(Diplome diplome){
        this.diplomes.add(diplome);
    }

    public void addCompetence(CompetencePoste competenceAnnonce){
        this.competencePostes.add(competenceAnnonce);
    }

    public void addExperiencePoste(ExperiencePoste experiencePoste){
        this.experiencePostes.add(experiencePoste);
    }

    public Poste(Long idPoste, String nomPoste, String descriptionPoste) {
        this.idPoste = idPoste;
        this.nomPoste = nomPoste;
        this.description = descriptionPoste;
    }

    public Poste(PosteDTO poste){
        this.setNomPoste(poste.getPoste());
        this.setDescription(poste.getDescription());
    }
}
