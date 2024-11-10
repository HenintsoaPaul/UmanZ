package mg.itu.rh.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.dto.EntretienCandidatureDTO;
import mg.itu.rh.other.POV;

@Entity
@Data
public class Entretien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entretien")
    @JsonView({POV.Public.class})
    private Long idEntretien;

    @Column(name = "date_creation", nullable = false)
    @JsonView({POV.Public.class})
    private LocalDate dateCreation;

    @Column(name = "date_validation")
    @JsonView({POV.Public.class})
    private LocalDate dateValidation;

    @Column(name = "note")
    @JsonView({POV.Public.class})
    private Integer note;

    @Column(name = "motif")
    @JsonView({POV.Public.class})
    private String motif;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_talent", nullable = false)
    @JsonView({POV.Public.class})
    private Talent talent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_annonce", nullable = false)
    @JsonView({POV.Full.class})
    private Annonce annonce;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_etat_entretien", nullable = false)
    @JsonView({POV.Public.class})
    private EtatEntretien etatEntretien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_enfant")
    @JsonView({POV.Public.class})
    private Entretien enfant;

    public Entretien() {}

    public Entretien(EntretienCandidatureDTO entretienDTO) {
        this.idEntretien = entretienDTO.getIdEntretien();
        this.dateCreation = entretienDTO.getDateCreation() != null ? entretienDTO.getDateCreation() : LocalDate.now();
        this.dateValidation = entretienDTO.getDateValidation();
        this.note = entretienDTO.getNote();
        this.motif = entretienDTO.getMotif();
    }
}
