package mg.itu.rh.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Formation {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column
    private Long idFormation;

    @Column( name = "nom_formation", length = 50 )
    private String nomFormation;

    @Column( name = "date_debut" )
    private LocalDate dateDebut;

    @Column( name = "date_fin" )
    private LocalDate dateFin;

    @OneToMany( fetch = FetchType.LAZY )
    @JoinColumn( name = "id_formation" )
    private List<CompetenceCible> competenceCibles;
}
