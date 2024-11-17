package mg.itu.rh.service;

import mg.itu.rh.entity.Talent;
import mg.itu.rh.repository.TalentRepository;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TalentService {
    @Autowired
    private TalentRepository talentRepository;
    
    @Autowired
    private CandidatHistoriqueService candidatHistoriqueService;

    @Autowired
    private EmailService emailService; 

    public Talent findById(Long id){
        return talentRepository.findById(id).orElseThrow(()->new RuntimeException("Talent non reconnue"));
    }

    public Talent findByEmailAndPassword(String email,String password){
        return talentRepository.findByEmailAndPassword(email,password).orElse(null);
    }

    public void prendreEntretien(String candidatEmail) {        
        try {
            Talent t = talentRepository.findByEmail(candidatEmail).orElse(null);
            String subject = "Entretien Planifié";
            String body = "<h1>Bonjour,</h1><p>Votre entretien a été planifié. Merci de confirmer votre présence.</p>";
            emailService.sendEmail(candidatEmail, subject, body);
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
            emailService.sendEmail(candidatEmail, subject, body);
            candidatHistoriqueService.ajouterHistoriqueEtNotifier(t, subject, body);
        } catch (Exception e) {
            System.out.println("Erreur d'envoi de l'e-mail : " + e.getMessage());
        }
    }

    public List<Talent> getAll() {
        return talentRepository.findAll(); 
    }    
}
