package mg.itu.rh.entity.recrutement;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import mg.itu.rh.other.POV;

@Entity
@Data
@Table(name="etat_entretien")
public class EtatEntretien {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_etat_entretien")
    @JsonView({POV.Public.class})
    private Long idEtatEntretien;

    @Column(name="etat_entretien")
    @JsonView({POV.Public.class})
    private String etatEntretien;

    private int niveau;
}
