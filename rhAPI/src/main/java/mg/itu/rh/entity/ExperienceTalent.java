package mg.itu.rh.entity;

import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.entity.id.IdExperienceTalent;

@Entity
@Data
@Table( name = "experience_talent" )
public class ExperienceTalent {
    @EmbeddedId
    private IdExperienceTalent id;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "id_poste", updatable = false, insertable = false )
    private Poste poste;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "id_talent", insertable = false, updatable = false )
    private Talent talent;

    private int ans;
}
