package mg.itu.rh.service;

import mg.itu.rh.dto.AnnonceDTO;
import mg.itu.rh.entity.Annonce;
import mg.itu.rh.repository.AnnonceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnonceService {
    private final AnnonceRepository annonceRepository;

    private final PosteService posteService;

    private final CompetenceAnnonceService competenceAnnonceService;

    private final ExperiencePosteService experiencePosteService;

    public AnnonceService( AnnonceRepository annonceRepository, PosteService posteService, CompetenceAnnonceService competenceAnnonceService, ExperiencePosteService experiencePosteService ) {
        this.annonceRepository = annonceRepository;
        this.posteService = posteService;
        this.competenceAnnonceService = competenceAnnonceService;
        this.experiencePosteService = experiencePosteService;
    }

    public List<Annonce> findAnnonceAvailable() {
        return annonceRepository.findAnnonceAvailable();
    }

    public Annonce findById( Long id ) {
        return annonceRepository.findById( id ).orElseThrow(
                () -> new RuntimeException( "Annonce non reconnue" ) );
    }

    public Annonce save( AnnonceDTO annonceDTO ) {
        Annonce annonce = new Annonce();
        annonce.setDateAnnonce( annonceDTO.getDateAnnonce() );
        annonce.setDateExpiration( annonceDTO.getDateExpiration() );
        annonce.setPoste( posteService.findById( annonceDTO.getIdPoste() ) );

        Annonce an = annonceRepository.save( annonce );
        // Save into tables liaisons
        experiencePosteService.saveAllFromDTO( annonceDTO.getExperiences(), an );
        competenceAnnonceService.saveAllFromDTO( annonceDTO.getCompetences(), an );
        return an;
    }
}
