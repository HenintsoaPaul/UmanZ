package mg.itu.rh.service.talent;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mg.itu.rh.dto.talent.TalentDTO;
import mg.itu.rh.entity.talent.Talent;

import java.util.List;

import mg.itu.rh.repository.talent.TalentRepository;
import mg.itu.rh.service.recrutement.CandidatHistoriqueService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TalentService {
    private final TalentRepository talentRepository;
    private final ExperienceTalentService experienceTalentService;
    private final CompetenceTalentService competenceTalentService;
    private final TalentDiplomeService talentDiplomeService;
    private final TalentLangueService talentLangueService;
    private final CandidatHistoriqueService candidatHistoriqueService;

    public Talent findById( Long id ) {
        return talentRepository.findById( id )
                .orElseThrow( () -> new RuntimeException( "Talent introuvable : idTalent = " +  id) );
    }

    public List<Talent> findAll() {
        return talentRepository.findAll();
    }

    @Transactional
    public Talent save( TalentDTO talentDTO ) {
        Talent t = new Talent( talentDTO );
        t = this.save( t );
        // set liaisons
        if ( talentDTO.getExperiences() != null )
            experienceTalentService.saveAllFromDTO( talentDTO.getExperiences(), t );
        if ( talentDTO.getCompetences() != null )
            competenceTalentService.saveAllFromDTO( talentDTO.getCompetences(), t );

        if ( talentDTO.getDiplomes() != null ) talentDiplomeService.saveAll( talentDTO.getDiplomes(), t );
        if ( talentDTO.getLangues() != null ) talentLangueService.saveAll( talentDTO.getLangues(), t );
        return t;
    }

    public Talent save( Talent talent ) {
        return this.talentRepository.save( talent );
    }

    public void prendreEntretien( String candidatEmail ) {
        try {
            Talent t = talentRepository.findByEmail( candidatEmail ).orElse( null );
            String subject = "Entretien Planifié";
            String body = "<h1>Bonjour,</h1><p>Votre entretien a été planifié. Merci de confirmer votre présence.</p>";
            // TODO: atao mande ny email
//            emailService.sendEmail(candidatEmail, subject, body);
            System.out.println( "Tsy mande ny email" );
            candidatHistoriqueService.ajouterHistoriqueEtNotifier( t, subject, body );
        } catch ( Exception e ) {
            System.out.println( "Erreur d'envoi de l'e-mail : " + e.getMessage() );
        }
    }

    public void rejetCandidat( String candidatEmail ) {
        try {
            Talent t = talentRepository.findByEmail( candidatEmail ).orElse( null );
            String subject = "Candidature rejetée";
            String body = "<h1>Bonjour,</h1><p>Nous sommes désolés de vous informer que votre candidature n'a pas été retenue.</p>";
            // TODO: atao mande ny email
//            emailService.sendEmail(candidatEmail, subject, body);
            System.out.println( "Tsy mande ny email" );
            candidatHistoriqueService.ajouterHistoriqueEtNotifier( t, subject, body );
        } catch ( Exception e ) {
            System.out.println( "Erreur d'envoi de l'e-mail : " + e.getMessage() );
        }
    }

    public List<Talent> getAll() {
        return talentRepository.findAll();
    }

//    public List<Talent> getEmployeesByCategory(Long idCategories) {
//        return talentRepository.findByCategoriesPosition_Id_IdCategories(idCategories);
//    }
}
