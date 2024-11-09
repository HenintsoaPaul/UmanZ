package mg.itu.rh.entity.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class IdExperienceTalent implements Serializable {
    @Column(name = "id_annonce")
    private Long idAnnonce;
    @Column(name = "id_poste")
    private Long idPoste;
}
