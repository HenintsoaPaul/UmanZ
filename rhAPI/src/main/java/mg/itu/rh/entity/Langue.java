package mg.itu.rh.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Langue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_langue")
    private Long idLangue;

    private String langue;
}
