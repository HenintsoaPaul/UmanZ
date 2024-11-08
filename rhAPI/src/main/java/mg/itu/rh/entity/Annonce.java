package mg.itu.rh.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Annonce {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_annonce")
    private Long idAnnonce;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_poste")
    private Poste poste;
    
    @OneToMany(mappedBy="annonce",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<ExperiencePoste> experiencePostes;
}
