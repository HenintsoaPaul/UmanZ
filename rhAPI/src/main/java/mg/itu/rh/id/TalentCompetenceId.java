package mg.itu.rh.id;

import java.io.Serializable;
import java.util.Objects;

public class TalentCompetenceId implements Serializable {

    private Long idTalent;
    private Long idCompetence;

    public TalentCompetenceId() {}

    public TalentCompetenceId(Long idTalent, Long idCompetence) {
        this.idTalent = idTalent;
        this.idCompetence = idCompetence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TalentCompetenceId that = (TalentCompetenceId) o;
        return idTalent.equals(that.idTalent) && idCompetence.equals(that.idCompetence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTalent, idCompetence);
    }
}
