package mg.itu.rh.entity.recrutement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.entity.interne.Poste;
import mg.itu.rh.other.POV;

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

    @OneToMany(mappedBy="annonce",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JsonView({POV.Full.class})
    private List<ExperiencePoste> experiencePostes=new ArrayList<ExperiencePoste>();

    public void addExperiencePoste(ExperiencePoste experiencePoste){
        this.experiencePostes.add(experiencePoste);
    }

}
