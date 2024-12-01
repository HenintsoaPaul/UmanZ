package mg.itu.rh.service.talent;

import jakarta.transaction.Transactional;
import mg.itu.rh.dto.talent.TalentDTO;
import mg.itu.rh.entity.talent.Talent;

import java.util.List;

import mg.itu.rh.repository.talent.TalentRepository;
import mg.itu.rh.service.interne.EmailService;
import mg.itu.rh.service.recrutement.CandidatHistoriqueService;
import org.springframework.stereotype.Service;

@Service
public class TalentService {
    private final TalentRepository talentRepository;
    private final ExperienceTalentService experienceTalentService;
    private final CompetenceTalentService competenceTalentService;
    private final TalentDiplomeService talentDiplomeService;
    private final TalentLangueService talentLangueService;
    private final CandidatHistoriqueService candidatHistoriqueService;
    private final EmailService emailService;

    public TalentService( TalentRepository talentRepository, ExperienceTalentService experienceTalentService, CompetenceTalentService competenceTalentService, TalentDiplomeService talentDiplomeService, TalentLangueService talentLangueService, CandidatHistoriqueService candidatHistoriqueService, EmailService emailService ) {
        this.talentRepository = talentRepository;
        this.experienceTalentService = experienceTalentService;
        this.competenceTalentService = competenceTalentService;
        this.talentDiplomeService = talentDiplomeService;
        this.talentLangueService = talentLangueService;
        this.candidatHistoriqueService = candidatHistoriqueService;
        this.emailService = emailService;
    }

    public Talent findById( Long id ) {
        return talentRepository.findById( id )
                .orElseThrow( () -> new RuntimeException( "Talent not found" ) );
    }

    public List<Talent> findAll(){
        return talentRepository.findAll();
    }

    public Talent findByEmailAndPassword( String email, String password ) {
        return talentRepository.findByEmailAndPassword( email, password ).orElse( null );
    }

    @Transactional
    public Talent save( TalentDTO talentDTO ) {
        Talent t = new Talent( talentDTO );
        t = this.save( t );
        // set liaisons
        experienceTalentService.saveAllFromDTO( talentDTO.getExperiences(), t );
        competenceTalentService.saveAllFromDTO( talentDTO.getCompetences(), t );

        talentDiplomeService.saveAll( talentDTO.getDiplomes(), t );
        talentLangueService.saveAll( talentDTO.getLangues(), t);
        return t;
    }

    public Talent save( Talent talent ) {
        return this.talentRepository.save( talent );
    }

    public void prendreEntretien(String candidatEmail) {
        try {
            Talent t = talentRepository.findByEmail(candidatEmail).orElse(null);
            String subject = "Entretien Planifié";
            String body = "<h1>Bonjour,</h1><p>Votre entretien a été planifié. Merci de confirmer votre présence.</p>";
            System.out.println("Tsy mande ny email");
            candidatHistoriqueService.ajouterHistoriqueEtNotifier(t, subject, body);
        } catch (Exception e) {
            System.out.println("Erreur d'envoi de l'e-mail : " + e.getMessage());
        }
    }

    public void rejetCandidat(String candidatEmail) {
        try {
            Talent t = talentRepository.findByEmail(candidatEmail).orElse(null);
            String subject = "Candidature rejetée";
            String body = "<h1>Bonjour,</h1><p>Nous sommes désolés de vous informer que votre candidature n'a pas été retenue.</p>";
            System.out.println("Tsy mande ny email");
            candidatHistoriqueService.ajouterHistoriqueEtNotifier(t, subject, body);
        } catch (Exception e) {
            System.out.println("Erreur d'envoi de l'e-mail : " + e.getMessage());
        }
    }

    public List<Talent> getAll() {
        return talentRepository.findAll();
    }

//    public List<Talent> getEmployeesByCategory(Long idCategories) {
//        return talentRepository.findByCategoriesPosition_Id_IdCategories(idCategories);
//    }
}
