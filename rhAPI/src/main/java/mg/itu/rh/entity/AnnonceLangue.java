package mg.itu.rh.entity;

import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.entity.id.IdAnnonceLangue;

@Entity
@Data
@Table(name="annonce_langue")
public class AnnonceLangue {
    @EmbeddedId
    private IdAnnonceLangue id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_annonce",insertable = false,updatable = false)
    private Annonce annonce;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_langue",insertable = false,updatable = false)
    private Langue langue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_niveau_langue",insertable = false,updatable = false)
    private NiveauLangue niveauLangue;
}
