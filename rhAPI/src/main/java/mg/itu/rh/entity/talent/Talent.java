package mg.itu.rh.entity.talent;

import java.time.LocalDate;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.itu.rh.dto.talent.TalentDTO;
import mg.itu.rh.entity.critere.Diplome;
import mg.itu.rh.other.POV;

@Entity
@Data
@NoArgsConstructor
public class Talent {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id_talent" )
    @JsonView( { POV.Public.class, POV.Emp.class, POV.Question.class, POV.Auth.class, POV.Conge.class, POV.Paie.class, POV.CandidatureResponse.class } )
    private Long idTalent;

    @JsonView( { POV.Public.class, POV.Emp.class, POV.Question.class, POV.Conge.class, POV.CandidatureResponse.class } )
    private String nom;

    @JsonView( { POV.Public.class, POV.Emp.class, POV.Question.class, POV.Conge.class, POV.CandidatureResponse.class } )
    private String prenom;

    @JsonView( { POV.Public.class, POV.Question.class } )
    @Column( name = "id_cnaps" )
    private String idCnaps;

    @JsonView( { POV.Public.class, POV.Auth.class } )
    private String mail;

    @Column( name = "password" )
    @JsonView( { POV.Public.class } )
    private String password;

    @Column( name = "date_naissance" )
    @JsonView( { POV.Public.class } )
    private LocalDate dateNaissance;

    @JsonView( { POV.Public.class, POV.Auth.class } )
    @Column( name = "is_admin" )
    private boolean isAdmin;

//    @ManyToOne
//    @JoinColumns({
//        @JoinColumn(name = "id_poste", referencedColumnName = "id_poste"),
//        @JoinColumn(name = "id_categories", referencedColumnName = "id_categories")
//    })
//    @JsonView({POV.Public.class})
//    private CategoriesPosition categoriesPosition;

//    @JsonView({POV.Public.class})
//    @Column(name = "date_of_hire")
//    private Date dateOfHire;

    @JsonView( { POV.Public.class } )
    @OneToMany( mappedBy = "talent", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<CompetenceTalent> competenceTalents;

    @JsonView( { POV.Public.class } )
    @OneToMany( mappedBy = "talent", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<ExperienceTalent> experienceTalents;

    @JsonView( { POV.Public.class } )
    @OneToMany( mappedBy = "talent", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<TalentLangue> talentLangues;

    @JsonView( { POV.Public.class } )
    @OneToMany( mappedBy = "talent", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<TalentCompetence> talentCompetences;

    @ManyToMany
    @JoinTable(
            name = "talent_diplome",
            joinColumns = @JoinColumn( name = "id_talent" ),
            inverseJoinColumns = @JoinColumn( name = "id_diplome" )
    )
    @JsonView( { POV.Public.class } )
    private Set<Diplome> diplomes;

    public Talent( TalentDTO talentDTO ) {
        this.setNom( talentDTO.getNom() );
        this.setPrenom( talentDTO.getPrenom() );
        this.setMail( talentDTO.getMail() );
        this.setPassword( talentDTO.getPassword() );
        this.setAdmin( talentDTO.isAdmin() );
        this.setDateNaissance( talentDTO.getDateNaissance() );
        this.setIdCnaps( talentDTO.getIdCnaps() );
    }

    @JsonView( { POV.Public.class,POV.Emp.class } )
    public String getNomPrenom() {
        return nom + " " + prenom;
    }
}
