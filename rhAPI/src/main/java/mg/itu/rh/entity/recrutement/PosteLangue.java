package mg.itu.rh.entity.recrutement;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.entity.critere.NiveauLangue;
import mg.itu.rh.entity.critere.Langue;
import mg.itu.rh.entity.id.IdPosteLangue;
import mg.itu.rh.entity.interne.Poste;
import mg.itu.rh.other.POV;

@Entity
@Data
@Table(name="langue_poste")
public class PosteLangue {
    @EmbeddedId
    private IdPosteLangue id=new IdPosteLangue();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idPoste")
    @JoinColumn(name = "id_poste",insertable = false,updatable = false)
    private Poste poste;

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
