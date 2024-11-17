package mg.itu.rh.entity;

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
import mg.itu.rh.other.POV;

@Entity
@Data
public class Diplome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_diplome")
    @JsonView({POV.Public.class})
    private Long idDiplome;

    @Column(name = "nom_diplome")
    @JsonView({POV.Public.class})
    private String nomDiplome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_niveau_diplome", nullable = false)
    @JsonView({POV.Public.class})
    private NiveauDiplome niveau_diplome;

    public Diplome() {}

    public Long getIdDiplome() {
        return idDiplome;
    }

    public void setIdDiplome(Long idDiplome) {
        this.idDiplome = idDiplome;
    }

    public String getNomDiplome() {
        return nomDiplome;
    }

    public void setNomDiplome(String nomDiplome) {
        this.nomDiplome = nomDiplome;
    }
}

