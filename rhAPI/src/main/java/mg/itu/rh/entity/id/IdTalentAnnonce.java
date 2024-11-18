package mg.itu.rh.entity.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Data
public class IdTalentAnnonce implements Serializable {
    @Column(name = "id_talent")
    private Long idTalent;
    @Column(name = "id_annonce")
    private Long idAnnonce;
    public IdTalentAnnonce(){}
    public IdTalentAnnonce(Long idAnnonce,Long idTalent){
        this.setIdAnnonce(idAnnonce);
        this.setIdTalent(idTalent);
    }
}
