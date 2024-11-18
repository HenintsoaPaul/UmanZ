package mg.itu.rh.entity;

import lombok.Data;
import mg.itu.rh.id.TalentDiplomeId;
import mg.itu.rh.other.POV;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.*;

@Entity
@Data
public class TalentDiplome {
    @EmbeddedId
    private TalentDiplomeId talentCompetenceId;

    @ManyToOne
    @MapsId("idTalent")
    @JoinColumn(name = "id_talent", insertable = false,updatable = false)
    // @JsonView( POV.Public.class )
    private Talent talent;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idDiplome")
    @JoinColumn(name = "id_diplome", insertable = false,updatable = false)
    // @JsonView( POV.Public.class )
    private Diplome diplome;

}
