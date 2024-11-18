package mg.itu.rh.controller;

import mg.itu.rh.entity.*;
import mg.itu.rh.other.POV;
import mg.itu.rh.repository.*;
import mg.itu.rh.service.TalentCompetenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;
import java.util.Optional;
import java.util.Comparator;

@RestController
@RequestMapping("/tricandidats")
public class TriCandidatsController {

    @Autowired
    private TalentRepository talentRepository;

    @Autowired
    private AnnonceRepository annonceRepository;

    @Autowired
    private TalentCompetenceService talentCompetenceService;

    @Autowired
    private TalentDiplomeRepository talentDiplomeRepository;

    @Autowired
    private ContratRepository contratRepository;

    @GetMapping("/trier")
    @JsonView( POV.Public.class )
    public List<Talent> trierCandidats(@RequestParam Long idAnnonce) {
        Optional<Annonce> annonceOptional = annonceRepository.findById(idAnnonce);

        if (!annonceOptional.isPresent()) {
            throw new RuntimeException("Annonce non trouvée");
        }

        Annonce annonce = annonceOptional.get();

        List<CompetenceAnnonce> competencesRequises = annonce.getCompetencesRequises();
        Diplome diplomesRequis = annonce.getDiplomesRequis();
        Poste postesRequis = annonce.getPostesRequis();

        List<Talent> talents = talentRepository.findAll();

        talents.forEach(talent -> {
            int scoreComp = calculerScoreCompetences(talent, competencesRequises);
            int scoreExperience = calculerScoreExperience(talent, postesRequis);
            int scoreEducation = calculerScoreEducation(talent, diplomesRequis);

            talent.setScoreTotal(scoreComp + scoreExperience + scoreEducation);
        });

        talents.sort(Comparator.comparingInt(Talent::getScoreTotal).reversed());

        return talents;
    }

    public int calculerScoreCompetences(Talent talent, List<CompetenceAnnonce> competencesAnnonces) {
        int score = 0;
    
        Long talentId = talent.getId();
    
        List<TalentCompetence> talentCompetences = talentCompetenceService.getCompetencesByTalentId(talentId);
    
        for (TalentCompetence talentCompetence : talentCompetences) {
            Competence competence = talentCompetence.getCompetence();
    
            Optional<CompetenceAnnonce> match = competencesAnnonces.stream()
                    .filter(competenceAnnonce -> competenceAnnonce.getCompetence().equals(competence))
                    .findFirst();
    
            if (match.isPresent()) {
                score += 10;  
            }
        }
    
        return score;
    }    

    private int calculerScoreExperience(Talent talent, Poste postesRequis) {
        int score = 0;
        List<Contrat> contrats = contratRepository.findByTalentId(talent.getId());
        for (Contrat contrat : contrats) {
            if (postesRequis.equals(contrat.getPoste())) {
                score += contrat.getDuree(); 
            }
        }
        return score;
    }

    private int calculerScoreEducation(Talent talent, Diplome diplomesRequis) {
        int score = 0;
        List<TalentDiplome> talentsDiplomes = talentDiplomeRepository.findByTalentId(talent.getId());
        for (TalentDiplome talentDiplome : talentsDiplomes) {
            if (talentDiplome.getDiplome().equals(diplomesRequis)) {
                score += 1; 
            }
        }
        return score;
    }
}
