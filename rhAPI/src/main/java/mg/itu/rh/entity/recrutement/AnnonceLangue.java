package mg.itu.rh.entity.recrutement;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.entity.critere.NiveauLangue;
import mg.itu.rh.entity.critere.Langue;
import mg.itu.rh.entity.id.IdAnnonceLangue;
import mg.itu.rh.other.POV;

@Entity
@Data
@Table(name="annonce_langue")
public class AnnonceLangue {
    @EmbeddedId
    private IdAnnonceLangue id=new IdAnnonceLangue();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idAnnonce")
    @JoinColumn(name = "id_annonce",insertable = false,updatable = false)
    private Annonce annonce;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idLangue")
    @JoinColumn(name = "id_langue",insertable = false,updatable = false)
    @JsonView({POV.Public.class})
    private Langue langue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonView({POV.Public.class})
    @MapsId("idNiveauLangue")
    @JoinColumn(name = "id_niveau_langue",insertable = false,updatable = false)
    private NiveauLangue niveauLangue;
}
