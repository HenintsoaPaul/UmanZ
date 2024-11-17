package mg.itu.rh.entity;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.other.POV;

@Entity
@Data
public class Talent {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_talent")
    @JsonView({POV.Public.class})
    private Long idTalent;

    @Column(name="nom")
    @JsonView({POV.Public.class})
    private String nom;

    @Column(name="prenom")
    @JsonView({POV.Public.class})
    private String prenom;

    @Column(name="mail")
    @JsonView({POV.Public.class})
    private String mail;

    @Column(name="password")
    @JsonView({POV.Public.class})
    private String password;

    @JsonView({POV.Public.class})
    @Column(name = "is_admin")
    private boolean isAdmin;

    // @ManyToMany
    // @JoinTable(
    //     name = "talent_competence",
    //     joinColumns = @JoinColumn(name = "id_talent"),
    //     inverseJoinColumns = @JoinColumn(name = "id_competence")
    // )
    
    public String getEmail(){
        return this.mail;
    }
}