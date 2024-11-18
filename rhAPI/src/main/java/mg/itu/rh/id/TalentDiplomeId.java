package mg.itu.rh.id;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class TalentDiplomeId implements Serializable {
    @Column(name = "id_talent")
    private Long idTalent;

    @Column(name = "id_diplome")
    private Long idDiplome;

    public TalentDiplomeId() {}

    public TalentDiplomeId(Long idTalent, Long idDiplome) {
        this.idTalent = idTalent;
        this.idDiplome = idDiplome;
    }
}
