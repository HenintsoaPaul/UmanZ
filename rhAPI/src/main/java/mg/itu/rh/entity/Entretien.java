package mg.itu.rh.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Entretien {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_entretien")
    private Long idEntretien;

    @Column(name="date_entretien")
    private LocalDate dateEntretien;

    private int note;

    private String motif;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_talent")
    private Talent talent;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_etat_entretien")
    private Entretien entretien;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_annonce")
    private Annonce annonce;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_etat_entretien")
    private EtatEntretien etatEntretien;
}
