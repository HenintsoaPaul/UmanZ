package mg.itu.rh.service;

import jakarta.transaction.Transactional;
import mg.itu.rh.dto.AnnonceDTO;
import mg.itu.rh.entity.Annonce;
import mg.itu.rh.entity.Compatibilite;
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

    public AnnonceService( AnnonceRepository annonceRepository, PosteService posteService, CompetenceAnnonceService competenceAnnonceService, ExperiencePosteService experiencePosteService, CompatibiliteService compatibiliteService ) {
        this.annonceRepository = annonceRepository;
        this.posteService = posteService;
        this.competenceAnnonceService = competenceAnnonceService;
        this.experiencePosteService = experiencePosteService;
        this.compatibiliteService = compatibiliteService;
    }

    public List<Compatibilite> findAnnonceAvailable(Long idTalent) {
        return compatibiliteService.findAllDispoByIdTalent(idTalent);
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

        Annonce an = annonceRepository.save( annonce );
        // Save into tables liaisons
        experiencePosteService.saveAllFromDTO( annonceDTO.getExperiences(), an );
        competenceAnnonceService.saveAllFromDTO( annonceDTO.getCompetences(), an );
        compatibiliteService.save(an);
        return an;
    }
}
