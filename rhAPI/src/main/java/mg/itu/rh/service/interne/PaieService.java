package mg.itu.rh.service.interne;

import jakarta.transaction.Transactional;
import mg.itu.rh.dto.interne.FicheDTO;
import mg.itu.rh.entity.interne.Contrat;
import mg.itu.rh.entity.talent.Talent;
import mg.itu.rh.repository.interne.ContratRepository;
import mg.itu.rh.service.talent.TalentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaieService {

    private final ContratRepository contratRepository;
    private final TalentService talentService;

    public PaieService( ContratRepository contratRepository, TalentService talentService ) {
        this.contratRepository = contratRepository;
        this.talentService = talentService;
    }

    private LocalDate getLastDateOfMonth( int month, int year ) {
        int nextMonth = month + 1;
        if ( nextMonth == 13 ) {
            year ++;
            nextMonth = 1;
        }
        return LocalDate.of( year, nextMonth, 1 ).minusDays( 1 );
    }

    @Transactional
    public FicheDTO findFiche( int annee, int mois, Long idTalent ) {
        return findFiche( getLastDateOfMonth( mois, annee ), idTalent );
    }

    @Transactional
    public FicheDTO findFiche( LocalDate lastDayOfMonth, Long idTalent ) {
        Talent talent = talentService.findById( idTalent );

        Contrat contratEmbauche = contratRepository.findContratEmbauche( idTalent )
                .orElseThrow( () -> new RuntimeException( "Cette personne n'a jamais travaille chez nous" ) );
        Contrat contratActuel = contratRepository.findContratByDateTalent( lastDayOfMonth, idTalent )
                .orElseThrow( () -> new RuntimeException( "Cette personne n'est pas un employe la date du " + lastDayOfMonth ) );

        return new FicheDTO( talent, contratEmbauche, contratActuel, lastDayOfMonth );
    }

    @Transactional
    public List<FicheDTO> findAllFichePaiesOnDate( int month, int year ) {
        List<FicheDTO> fichePaies = new ArrayList<>();
        LocalDate lastDayOfMonth = getLastDateOfMonth( month, year );
        List<Contrat> contratsEnCours = this.contratRepository.findAllContratEnCoursOnDate( lastDayOfMonth );

        for ( Contrat c : contratsEnCours ) {
            fichePaies.add( this.findFiche( lastDayOfMonth, c.getTalent().getIdTalent() ) );
        }
        return fichePaies;
    }
}
