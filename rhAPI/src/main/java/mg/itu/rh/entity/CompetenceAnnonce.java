package mg.itu.rh.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.entity.id.IdCompetenceAnnonce;
import mg.itu.rh.other.POV;

@Entity
@Data
@Table(name = "competence_annonce")
public class CompetenceAnnonce {
    @EmbeddedId
    private IdCompetenceAnnonce id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonView({POV.Full.class})
    @JoinColumn(name = "id_competence",insertable = false,updatable = false)
    private Competence competence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_annonce",insertable = false,updatable = false)
    private Annonce annonce;

    @JsonView({POV.Public.class})
    private int point;
}
