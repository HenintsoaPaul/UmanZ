package mg.itu.rh.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idFormation;

    @Column(length = 50)
    private String nomFormation;

    @Column
    private LocalDate dateDebut;

    @Column
    private LocalDate dateFin;
}
