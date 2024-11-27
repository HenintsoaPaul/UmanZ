package mg.itu.rh.dto.interne;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.Getter;
import mg.itu.rh.entity.interne.Contrat;
import mg.itu.rh.entity.talent.Talent;
import mg.itu.rh.other.POV;

import java.time.LocalDate;

@Getter
public class FicheDTO {
    private Talent talent;
    private Contrat contratEmbauche;
    private Contrat contratActuel;

    @JsonView({POV.Public.class})
    private String nomPrenom;
    @JsonView({POV.Public.class})
    private Long matricule;
    @JsonView({POV.Public.class})
    private String fonction;
    @JsonView({POV.Public.class})
    private LocalDate dateEmbauche;
    @JsonView({POV.Public.class})
    private String anciennete;
    @JsonView({POV.Public.class})
    private String classification;
    @JsonView({POV.Public.class})
    private String idCnaps;
    @JsonView({POV.Public.class})
    private double salaire;
    @JsonView({POV.Public.class})
    private double tauxJournalier;
    @JsonView({POV.Public.class})
    private double tauxHoraire;
    @JsonView({POV.Public.class})
    private double indice;

    protected void setNomPrenom(){
        this.nomPrenom=this.getTalent().getNom()+" "+this.getTalent().getPrenom();
    }

    protected void setMatricule(){
        this.matricule=this.getTalent().getIdTalent();
    }

    protected void setFonction(){
        this.fonction=this.contratActuel.getContrat();
    }

    protected void setDateEmbauche(){
        this.dateEmbauche=this.contratEmbauche.getDateDebut();
    }

    protected void setIdCnaps(){
        this.idCnaps="10";
    }

    protected void setAnciennete(){
        this.anciennete="12 ans";
    }

    protected void setClassification(){
        this.classification="HC";
    }

    protected void setSalaire(){
        double nbHeure=24.0;
        if(this.getContratActuel().getNbHeureJour()!=8.0){
            nbHeure=this.getContratActuel().getNbHeureJour();
        }
        this.salaire=this.getContratActuel().getSalaireHoraire()*nbHeure*30.0;
        this.tauxJournalier=(int)((this.salaire/173.33)*nbHeure);
        this.tauxHoraire=(int)(this.salaire/173.33);
        this.indice=(int)(this.getTauxJournalier()/1.334);
    }

    public void setTalent(Talent talent){
        this.talent=talent;
        this.setNomPrenom();
        this.setMatricule();
        this.setFonction();
        this.setIdCnaps();
    }

    public void setContratActuel(Contrat contrat){
        this.contratActuel=contrat;
        this.setDateEmbauche();
    }

    public void setContratEmbauche(Contrat contrat){
        this.contratEmbauche=contrat;
        this.setClassification();
        this.setSalaire();
    }

    public FicheDTO(Talent talent,Contrat contratEmbauche,Contrat contratActuel){
        this.setTalent(talent);
        this.setContratEmbauche(contratEmbauche);
        this.setContratActuel(contratActuel);
    }
}
