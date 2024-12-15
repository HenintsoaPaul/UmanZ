package mg.itu.rh.entity.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Data
public class IdTalentPoste implements Serializable {
    @Column(name = "id_talent")
    private Long idTalent;
    @Column(name = "id_poste")
    private Long idPoste;
    public IdTalentPoste(){}
    public IdTalentPoste(Long idPoste, Long idTalent){
        this.setIdPoste(idPoste);
        this.setIdTalent(idTalent);
    }
}
