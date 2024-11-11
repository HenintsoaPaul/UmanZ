package mg.itu.rh.entity;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.entity.id.IdExperiencePoste;
import mg.itu.rh.other.POV;

@Entity
@Data
@Table(name = "experience_poste")
public class ExperiencePoste {
    @EmbeddedId
    private IdExperiencePoste id;

    @ManyToOne(fetch=FetchType.LAZY)
    @MapsId("idPoste")
    @JoinColumn(name="id_poste",insertable=false,updatable=false)
    @JsonView({POV.Public.class})
    private Poste poste;

    @MapsId("idAnnonce")
    @ManyToOne(fetch=FetchType.LAZY)
    @JsonView({POV.Public.class})
    @JoinColumn(name="id_annonce",insertable=false,updatable=false)
    private Annonce annonce;

    @JsonView({POV.Public.class})
    private int ans;
}
