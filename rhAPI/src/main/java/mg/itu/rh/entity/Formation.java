package mg.itu.rh.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.other.POV;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Formation {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column
    @JsonView( POV.Public.class )
    private Long idFormation;

    @Column( name = "nom_formation", length = 50 )
    @JsonView( POV.Public.class )
    private String nomFormation;

    @Column( name = "date_debut" )
    @JsonView( POV.Public.class )
    private LocalDate dateDebut;

    @Column( name = "date_fin" )
    @JsonView( POV.Public.class )
    private LocalDate dateFin;

    @OneToMany( fetch = FetchType.LAZY )
    @JoinColumn( name = "id_formation" )
    @JsonView( POV.Public.class )
    private List<CompetenceCible> competenceCibles;
}
