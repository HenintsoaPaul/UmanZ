package mg.itu.rh.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class TalentDiplome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_talent")
    private Talent talent;

    @ManyToOne
    @JoinColumn(name = "id_diplome")
    private Diplome diplome;

}
