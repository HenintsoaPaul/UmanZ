package mg.itu.rh.entity.question;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Domaine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_domaine")
    private Long idDomaine;

    private String domaine;
}
