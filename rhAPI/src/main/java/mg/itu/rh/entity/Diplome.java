package mg.itu.rh.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.other.POV;

@Entity
@Data
public class Diplome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_diplome")
    // @JsonView({POV.Public.class})
    private Long idDiplome;

    @Column(name = "nom_diplome")
    // @JsonView({POV.Public.class})
    private String nomDiplome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_niveau_diplome", nullable = false)
    // @JsonView({POV.Public.class})
    private NiveauDiplome niveau_diplome;

    @OneToMany(mappedBy = "diplome", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    // @JsonView({POV.Public.class})
    private List<TalentDiplome> talentsDiplomes = new ArrayList<>();

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

package mg.itu.rh.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.other.POV;

@Data
@Entity
public class Diplome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({POV.Public.class})
    @Column(name="id_diplome")
    private Long idDiplome;

    @JsonView({POV.Public.class})
    @Column(name = "nom_diplome")
    private String diplome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_niveau_diplome")
    @JsonView({POV.Public.class})
    private NiveauDiplome niveauDiplome;
}
