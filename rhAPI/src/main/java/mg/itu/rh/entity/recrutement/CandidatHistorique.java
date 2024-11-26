package mg.itu.rh.entity.recrutement;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
import mg.itu.rh.entity.talent.Talent;
import mg.itu.rh.other.POV;

@Entity
@Data
public class CandidatHistorique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_candidat_histo")
    @JsonView({POV.Public.class})
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_talent", referencedColumnName = "id_talent", nullable = false)
    @JsonView({POV.Public.class})
    private Talent talent;

    @Column(name = "action")
    @JsonView({POV.Public.class})
    private String action; 

    @Column(name = "description")
    @JsonView({POV.Public.class})
    private String description; 

    @Column(name = "date_action")
    @JsonView({POV.Public.class})
    private LocalDateTime dateAction;

    public CandidatHistorique() {
    }

    public Talent getCandidat() {
        return talent;
    }

    public void setCandidat(Talent talent) {
        this.talent = talent;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateAction() {
        return dateAction;
    }

    public void setDateAction(LocalDateTime dateAction) {
        this.dateAction = dateAction;
    }
}
