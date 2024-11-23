package mg.itu.rh.entity.recrutement;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.entity.critere.Diplome;
import mg.itu.rh.entity.id.IdTalentAnnonce;
import mg.itu.rh.entity.talent.ExperienceTalent;
import mg.itu.rh.entity.talent.Talent;
import mg.itu.rh.entity.talent.TalentCompetence;
import mg.itu.rh.entity.talent.TalentLangue;
import mg.itu.rh.other.POV;

import java.util.List;
import java.util.Set;

@Entity
@Data
public class Compatibilite {
    @EmbeddedId
    private IdTalentAnnonce idTalentAnnonce = new IdTalentAnnonce();

    @ManyToOne( fetch = FetchType.LAZY )
    @MapsId( "idAnnonce" )
    @JoinColumn( name = "id_annonce", insertable = false, updatable = false )
    @JsonView( { POV.Public.class } )
    private Annonce annonce;

    @ManyToOne( fetch = FetchType.LAZY )
    @MapsId( "idTalent" )
    @JoinColumn( name = "id_talent", insertable = false, updatable = false )
    private Talent talent;

    @JsonView( { POV.Public.class } )
    private double pourcentage;

    protected final static double coefDiplome = 4;
    protected final static double coefExperience = 4;
    protected final static double coefLangue = 2;
    protected final static double coefCompetence = 1;

    public Compatibilite() {
    }

    protected void setPourcentage( double pourcentage ) {
        this.pourcentage = pourcentage;
    }

    protected void setPourcentage() {
        double pointPour = 0;
        double point = ( calculateByDiplome() * coefDiplome );
        pointPour += point;
        point = ( calculateByCompetence() * coefCompetence );
        pointPour += point;
        point = ( calculateByExperience() * coefExperience );
        pointPour += point;
        point = ( calculateByLangue() * coefLangue );
        pointPour += point;
        double coefTotal = coefCompetence + coefLangue + coefExperience + coefDiplome;
        System.out.println( pointPour + " " + coefTotal + " pourcentage mahafinatitra" );
        this.setPourcentage( pointPour / coefTotal * 100.0 / 20.0 );
    }

    public Compatibilite( Annonce annonce, Talent talent ) {
        //this.setIdTalentAnnonce(new IdTalentAnnonce(annonce.getIdAnnonce(),talent.getIdTalent()));
        this.setAnnonce( annonce );
        this.setTalent( talent );
        System.out.println( "Tonga eto" );
        this.setPourcentage();
    }

    public void setPourcentageAnnonce(){
        this.getAnnonce().setPourcentage(this.getPourcentage());
    }

    protected double calculateByDiplome() {
        double max = 0;
        Set<Diplome> diplomesAnnonce = this.getAnnonce().getDiplomes();
        Set<Diplome> diplomesTalent = this.talent.getDiplomes();
        for ( Diplome diplomeAnnonce : diplomesAnnonce ) {
            for ( Diplome diplomeTalent : diplomesTalent ) {
                double point = calculateByDiplome( diplomeAnnonce, diplomeTalent );
                if ( max < point ) {
                    max = point;
                }
            }
        }
        return max;
    }

    protected double calculateByCompetence() {
        double point = 0;
        List<CompetenceAnnonce> competenceAnnonces = this.getAnnonce().getCompetenceAnnonces();
        List<TalentCompetence> talentCompetences = this.talent.getTalentCompetences();
        for ( CompetenceAnnonce competenceAnnonce : competenceAnnonces ) {
            boolean matched = false;
            for ( TalentCompetence talentCompetence : talentCompetences ) {
                if ( talentCompetence.getCompetence().getIdCompetence().equals( competenceAnnonce.getCompetence().getIdCompetence() ) ) {
                    point += calculateByCompetence( competenceAnnonce, talentCompetence );
                    matched = true;
                }
            }
            if ( !matched ) {
                point += 3;
            }
        }
        if ( competenceAnnonces.isEmpty() ) {
            return 20.0;
        }
        return point / competenceAnnonces.size();
    }

    protected double calculateByLangue() {
        double point = 0;
        List<AnnonceLangue> annonceLangues = this.getAnnonce().getAnnonceLangues();
        List<TalentLangue> talentLangues = this.talent.getTalentLangues();
        for ( AnnonceLangue annonceLangue : annonceLangues ) {
            boolean matched = false;
            for ( TalentLangue talentLangue : talentLangues ) {
                if ( annonceLangue.getLangue().getIdLangue().equals( talentLangue.getLangue().getIdLangue() ) ) {
                    point += calculateByLangue( annonceLangue, talentLangue );
                    matched = true;
                }
            }
            if ( !matched ) {
                point += 3;
            }
        }
        if ( annonceLangues.isEmpty() ) {
            return 20.0;
        }
        return point / annonceLangues.size();
    }

    protected double calculateByExperience() {
        double max = 0;
        List<ExperienceTalent> experienceTalents = this.getTalent().getExperienceTalents();
        List<ExperiencePoste> experiencePostes = this.annonce.getExperiencePostes();
        for ( ExperiencePoste experiencePoste : experiencePostes ) {
            for ( ExperienceTalent experienceTalent : experienceTalents ) {
                double point = calculateByExperience( experiencePoste, experienceTalent );
                if ( max < point ) {
                    max = point;
                }
            }
        }
        return max;
    }

    protected static double calculateByDiplome( Diplome diplomeAnnonce, Diplome diplomeTalent ) {
        if ( diplomeTalent.getDiplome().compareTo( diplomeTalent.getDiplome() ) == 0 ) {
            if ( diplomeAnnonce.getNiveauDiplome().getNiveau() > diplomeTalent.getNiveauDiplome().getNiveau() ) {
                return 10;
            } else if ( diplomeAnnonce.getNiveauDiplome().getNiveau() == diplomeTalent.getNiveauDiplome().getNiveau() ) {
                return 15;
            }
            return 20;
        }
        return 3.0;
    }

    protected static double calculateByCompetence( CompetenceAnnonce competenceAnnonce, TalentCompetence competenceTalent ) {
        if ( competenceAnnonce.getPoint() > competenceTalent.getPoint() ) {
            return 10;
        } else if ( competenceAnnonce.getPoint() == competenceTalent.getPoint() ) {
            return 15;
        }
        return 20;
    }

    protected static double calculateByLangue( AnnonceLangue annonceLangue, TalentLangue talentLangue ) {
        if ( annonceLangue.getNiveauLangue().getNiveau() > talentLangue.getNiveauLangue().getNiveau() ) {
            return 10;
        } else if ( annonceLangue.getNiveauLangue().getNiveau() == talentLangue.getNiveauLangue().getNiveau() ) {
            return 15;
        }
        return 20;
    }

    protected static double calculateByExperience( ExperiencePoste experiencePoste, ExperienceTalent experienceTalent ) {
        if ( experiencePoste.getPoste().getIdPoste().equals( experienceTalent.getPoste().getIdPoste() ) ) {
            if ( experiencePoste.getAns() > experienceTalent.getAns() ) {
                return 10.0;
            } else if ( experiencePoste.getAns() == experienceTalent.getAns() ) {
                return 15.0;
            }
            return 20.0;
        }
        return 3.0;
    }
}
