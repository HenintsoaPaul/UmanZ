package mg.itu.rh.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Competence {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_competence")
    private Long idCompetence;

    private String competence;
}
