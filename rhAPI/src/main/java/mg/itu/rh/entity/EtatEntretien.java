package mg.itu.rh.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="etat_entretien")
public class EtatEntretien {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_etat_entretien")
    private Long idEtatEntretien;

    @Column(name="etat_entretien")
    private String etatEntretien;
}
