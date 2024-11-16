package mg.itu.rh.entity.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Data
public class IdAnnonceLangue implements Serializable {
    private Long idAnnonce;
    private Long idLangue;
    private Long idNiveauLangue;
    public IdAnnonceLangue(){}
    /*public IdAnnonceLangue(Long idAnnonce,Long idLangue,Long idNiveauLangue){
        this.setIdAnnonce(idAnnonce);
        this.setIdLangue(idLangue);
        this.setIdNiveauLangue(idNiveauLangue);
    }*/
    public void setIdAnnonce(Long idAnnonce){
        this.setIdAnnonce(idAnnonce);
    }
}
