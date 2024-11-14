package mg.itu.rh.entity.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class IdAnnonceLangue implements Serializable {
    @Column(name = "id_annonce")
    private Long idAnnonce;
    @Column(name = "id_langue")
    private Long idLangue;
    @Column(name = "id_niveau_langue")
    private Long idNiveauLangue;
}
