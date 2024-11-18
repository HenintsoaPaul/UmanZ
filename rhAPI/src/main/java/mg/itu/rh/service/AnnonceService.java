package mg.itu.rh.service;

import jakarta.transaction.Transactional;
import mg.itu.rh.dto.AnnonceDTO;
import mg.itu.rh.entity.*;
import mg.itu.rh.repository.AnnonceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnonceService {
    private final AnnonceRepository annonceRepository;
    private final PosteService posteService;
    private final CompetenceAnnonceService competenceAnnonceService;
    private final ExperiencePosteService experiencePosteService;
    private final CompatibiliteService compatibiliteService;
    private final AnnonceLangueService annonceLangueService;
    private final AnnonceDiplomeService annonceDiplomeService;

    public AnnonceService( AnnonceRepository annonceRepository, PosteService posteService, CompetenceAnnonceService competenceAnnonceService, ExperiencePosteService experiencePosteService, CompatibiliteService compatibiliteService, AnnonceLangueService annonceLangueService, AnnonceDiplomeService annonceDiplomeService ) {
        this.annonceRepository = annonceRepository;
        this.posteService = posteService;
        this.competenceAnnonceService = competenceAnnonceService;
        this.experiencePosteService = experiencePosteService;
        this.compatibiliteService = compatibiliteService;
        this.annonceLangueService = annonceLangueService;
        this.annonceDiplomeService = annonceDiplomeService;
    }

    public List<Compatibilite> findAnnonceAvailable( Long idTalent ) {
        return compatibiliteService.findAllDispoByIdTalent( idTalent );
    }

    public Annonce findById( Long id ) {
        return annonceRepository.findById( id ).orElseThrow(
                () -> new RuntimeException( "Annonce non reconnue" ) );
    }

    @Transactional
    public Annonce save( AnnonceDTO annonceDTO ) {
        Annonce annonce = new Annonce();
        annonce.setDateAnnonce( annonceDTO.getDateAnnonce() );
        annonce.setDateExpiration( annonceDTO.getDateExpiration() );
        annonce.setPoste( posteService.findById( annonceDTO.getIdPoste() ) );
        annonce = annonceRepository.save( annonce );

        // Save into tables liaisons
        experiencePosteService.saveAllFromDTO( annonceDTO.getExperiences(), annonce );
        competenceAnnonceService.saveAllFromDTO( annonceDTO.getCompetences(), annonce );

        annonceDiplomeService.saveAll( annonceDTO.getDiplomes(), annonce );
        annonceLangueService.saveAll( annonceDTO.getLangues(), annonce);

        compatibiliteService.save( annonce );
        return annonce;
    }

    public List<Annonce> findAllDisponible() {
        return annonceRepository.findAnnonceAvailable();
    }
}
