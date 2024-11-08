package mg.itu.rh.entity;

import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.entity.id.IdExperienceTalent;

@Entity
@Data
public class ExperienceTalent {
    @EmbeddedId
    private IdExperienceTalent id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_poste")
    private Poste poste;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_talent")
    private Talent talent;

    private int ans;
}
