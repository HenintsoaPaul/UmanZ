package mg.itu.rh.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import mg.itu.rh.other.POV;

@Entity
@Data
public class Competence {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_competence")
    @JsonView({POV.Public.class})
    private Long idCompetence;

    @JsonView({POV.Public.class})
    private String competence;

    public Competence() {}

    public Competence(Long idCompetence, String competence) {
        this.idCompetence = idCompetence;
        this.competence = competence;
    }
}
