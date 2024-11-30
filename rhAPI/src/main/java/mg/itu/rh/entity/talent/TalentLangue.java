package mg.itu.rh.entity.talent;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.entity.critere.Langue;
import mg.itu.rh.entity.critere.NiveauLangue;
import mg.itu.rh.entity.id.IdTalentLangue;
import mg.itu.rh.other.POV;

@Entity
@Data
@Table(name = "talent_langue")
public class TalentLangue {
    @EmbeddedId
    private IdTalentLangue id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonView({POV.Public.class})
    @JoinColumn(name="id_langue",insertable = false,updatable = false)
    private Langue langue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonView({POV.Public.class})
    @JoinColumn(name="id_niveau_langue",insertable = false,updatable = false)
    private NiveauLangue niveauLangue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_talent",insertable = false,updatable = false)
    private Talent talent;
}
