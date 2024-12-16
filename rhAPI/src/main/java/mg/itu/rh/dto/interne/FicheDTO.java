package mg.itu.rh.dto.interne;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import mg.itu.rh.entity.interne.Contrat;
import mg.itu.rh.entity.talent.Talent;
import mg.itu.rh.other.POV;

import java.time.LocalDate;
import java.time.Period;

@Getter
public class FicheDTO {
    public final static double PLAFOND_CNAPS = 20_000;

    @Setter
    private LocalDate date;
    private Contrat contratEmbauche;
    private Contrat contratActuel;

    @JsonView( { POV.Paie.class } )
    private Talent talent;

    @JsonView( { POV.Paie.class } )
    private String nomPrenom;

    @JsonView( { POV.Paie.class } )
    private Long matricule;

    @JsonView( { POV.Paie.class } )
    private String fonction;

    @JsonView( { POV.Paie.class } )
    private LocalDate dateEmbauche;

    @JsonView( { POV.Paie.class } )
    private String anciennete;

    @JsonView( { POV.Paie.class } )
    private String classification;

    @JsonView( { POV.Paie.class } )
    private String idCnaps;

    @JsonView( { POV.Paie.class } )
    private double salaire;

    @JsonView( { POV.Paie.class } )
    private double tauxJournalier;

    @JsonView( { POV.Paie.class } )
    private double tauxHoraire;

    @JsonView( { POV.Paie.class } )
    private double indice;

    @JsonView( { POV.Paie.class } )
    private final double retenueCnaps;

    @JsonView( { POV.Paie.class } )
    private final double retenueSanitaire;

    protected void setNomPrenom() {
        this.nomPrenom = this.getTalent().getNom() + " " + this.getTalent().getPrenom();
    }

    protected void setMatricule() {
        this.matricule = this.getTalent().getIdTalent();
    }

    protected void setFonction() {
        this.fonction = this.contratActuel.getPoste().getNomPoste();
    }

    protected void setDateEmbauche() {
        this.dateEmbauche = this.contratEmbauche.getDateDebut();
    }

    protected void setIdCnaps() {
        this.idCnaps = this.getTalent().getIdCnaps();
    }

    protected void setAnciennete() {
        Period period = Period.between( this.getContratEmbauche().getDateDebut(), this.getDate() );
        String value = "";
        if ( period.getYears() != 0 ) {
            value += period.getYears() + " ans ";
        }
        if ( period.getMonths() != 0 ) {
            value += period.getMonths() + " mois";
        }
        if ( period.getDays() != 0 ) {
            if ( !value.isEmpty() ) {
                value += " et ";
            }
            value += period.getDays() + " jours";
        }
        this.anciennete = value;
    }

    protected void setClassification() {
        this.classification = this.getContratActuel().getPoste().getTypePoste().getTypePoste();
    }

    protected void setSalaire() {
        double nbHeureMois = 173.33;
        double nbHeure = 24.0;
        if ( this.getContratActuel().getNbHeureJour() < 8.0 ) {
            nbHeureMois = this.getContratActuel().getNbHeureJour() * this.getContratActuel().getNbJourSemaine() * 4;
            nbHeure = this.getContratActuel().getNbHeureJour();
        }
        this.salaire = this.getContratActuel().getSalaireHoraire() * nbHeureMois;
        this.tauxJournalier = ( int ) ( this.getContratActuel().getSalaireHoraire() * nbHeure );
        this.tauxHoraire = ( int ) this.getContratActuel().getSalaireHoraire();
        this.indice = ( int ) ( this.getTauxJournalier() / 1.334 );
    }

    public void setTalent( Talent talent ) {
        this.talent = talent;
        this.setNomPrenom();
        this.setMatricule();
        this.setIdCnaps();
    }

    public void setContratActuel( Contrat contrat ) {
        this.contratActuel = contrat;
        this.setClassification();
        this.setSalaire();
        this.setFonction();
    }

    public void setContratEmbauche( Contrat contrat ) {
        this.contratEmbauche = contrat;
        this.setDateEmbauche();
    }

    public FicheDTO( Talent talent, Contrat contratEmbauche, Contrat contratActuel, LocalDate date, double retenueCnaps, double retenueSanitaire ) {
        this.setDate( date );
        this.setTalent( talent );
        this.setContratEmbauche( contratEmbauche );
        this.setContratActuel( contratActuel );
        this.setAnciennete();
        this.retenueCnaps = retenueCnaps;
        this.retenueSanitaire = retenueSanitaire;
    }
}
