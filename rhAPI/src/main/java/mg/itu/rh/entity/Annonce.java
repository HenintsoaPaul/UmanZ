package mg.itu.rh.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.DiplomeService;

@Entity
@Data
public class Annonce {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_annonce")
    @JsonView({POV.Public.class})
    private Long idAnnonce;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_poste")
    @JsonView({POV.Public.class})
    private Poste poste;

    @JsonView({POV.Public.class})
    @Column(name = "date_annonce")
    private LocalDate dateAnnonce;

    @JsonView({POV.Public.class})
    @Column(name = "date_expiration")
    private LocalDate dateExpiration;

    @JsonView({POV.Public.class})
    @Column(name = "date_rupture")
    private LocalDate dateRupture;

    @OneToMany(mappedBy = "annonce",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonView({POV.Full.class})
    private List<CompetenceAnnonce> competenceAnnonces=new ArrayList<CompetenceAnnonce>();

    @OneToMany(mappedBy="annonce",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JsonView({POV.Full.class})
    private List<ExperiencePoste> experiencePostes=new ArrayList<ExperiencePoste>();

    @ManyToMany
    @JoinTable(
            name = "annonce_diplome",
            joinColumns = @JoinColumn(name = "id_annonce"),
            inverseJoinColumns = @JoinColumn(name = "id_diplome")
    )
    @JsonView({POV.Public.class})
    private Set<Diplome> diplomes=new HashSet<Diplome>();

    @OneToMany(mappedBy="annonce",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JsonView({POV.Public.class})
    private List<AnnonceLangue> annonceLangues=new ArrayList<AnnonceLangue>();

    @Transient
    @JsonView({POV.Public.class})
    private double pourcentage;

    public void addDiplome(Diplome diplome){
        this.diplomes.add(diplome);
    }

    public void addCompetence(CompetenceAnnonce competenceAnnonce){
        this.competenceAnnonces.add(competenceAnnonce);
    }

    public void addExperiencePoste(ExperiencePoste experiencePoste){
        this.experiencePostes.add(experiencePoste);
    }

}
