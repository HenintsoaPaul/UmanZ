package mg.itu.rh.entity.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdExperiencePoste implements Serializable {
    @Column(name = "id_annonce")
    private Long idAnnonce;
    @Column(name = "id_poste")
    private Long idPoste;
}
