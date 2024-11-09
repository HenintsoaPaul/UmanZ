package mg.itu.rh.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import mg.itu.rh.dto.EntretienCandidatureDTO;
import mg.itu.rh.other.POV;

@Entity
@Data
public class Entretien {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_entretien")
    @JsonView({POV.Public.class})
    private Long idEntretien;

    @Column(name="date_creation")
    @JsonView({POV.Public.class})
    private LocalDate dateCreation;

    @Column(name="date_validation")
    @JsonView({POV.Public.class})
    private LocalDate dateValidation;

    @JsonView({POV.Public.class})
    private int note;

    @JsonView({POV.Public.class})
    private String motif;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_talent")
    @JsonView({POV.Public.class})
    private Talent talent;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_annonce")
    @JsonView({POV.Full.class})
    private Annonce annonce;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_etat_entretien")
    @JsonView({POV.Public.class})
    private EtatEntretien etatEntretien;

    public Entretien(EntretienCandidatureDTO entretienDTO){
        this.setIdEntretien(entretienDTO.getIdEntretien());
        this.setDateCreation(entretienDTO.getDateCreation());
        if(this.getDateCreation()==null){
            this.setDateCreation(LocalDate.now());
        }
        this.setDateValidation(entretienDTO.getDateValidation());
        this.setNote(entretienDTO.getNote());
    }
}
