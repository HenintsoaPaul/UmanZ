package mg.itu.rh.entity.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Data
@NoArgsConstructor
public class IdCompetencePoste implements Serializable {
    @Column(name = "id_competence")
    private Long idCompetence;
    @Column(name = "id_poste")
    private Long idPoste;

    public IdCompetencePoste(Long idCompetence, Long idPoste) {
        this.idCompetence = idCompetence;
        this.idPoste = idPoste;
    }
}
