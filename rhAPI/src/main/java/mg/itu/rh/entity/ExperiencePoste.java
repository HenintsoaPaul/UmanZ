package mg.itu.rh.entity;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;
import mg.itu.rh.entity.id.IdExperiencePoste;
import mg.itu.rh.other.POV;

@Entity
@Data
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
    @JoinColumn(name="id_annonce",insertable=false,updatable=false)
    private Annonce annonce;

    @JsonView({POV.Public.class})
    private int ans;
}
