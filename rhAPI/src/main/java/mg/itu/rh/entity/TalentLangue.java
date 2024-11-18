package mg.itu.rh.entity;

import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.entity.id.IdTalentLangue;

@Entity
@Data
@Table(name = "talent_langue")
public class TalentLangue {
    @EmbeddedId
    private IdTalentLangue id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_langue",insertable = false,updatable = false)
    private Langue langue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_niveau_langue",insertable = false,updatable = false)
    private NiveauLangue niveauLangue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_talent",insertable = false,updatable = false)
    private Talent talent;
}
