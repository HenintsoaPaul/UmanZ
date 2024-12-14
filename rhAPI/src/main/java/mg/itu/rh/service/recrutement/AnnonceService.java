package mg.itu.rh.service.recrutement;

import jakarta.transaction.Transactional;
import mg.itu.rh.dto.recrutement.AnnonceDTO;
import mg.itu.rh.entity.recrutement.Annonce;
import mg.itu.rh.repository.recrutement.AnnonceRepository;
import mg.itu.rh.service.interne.PosteService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnonceService {
    private final AnnonceRepository annonceRepository;
    private final PosteService posteService;
    private final CompatibiliteService compatibiliteService;

    private final ExperiencePosteService experiencePosteService;

    public AnnonceService(AnnonceRepository annonceRepository, PosteService posteService, CompatibiliteService compatibiliteService, ExperiencePosteService experiencePosteService) {
        this.annonceRepository = annonceRepository;
        this.posteService = posteService;
        this.compatibiliteService = compatibiliteService;
        this.experiencePosteService = experiencePosteService;
    }

    public List<Long> findAnnoncePostuledByTalent(Long idTalent){
        return annonceRepository.findAnnoncePostuledByTalent(idTalent);
    }

    public List<Annonce> findAnnonceAvailable(Long idTalent ) {
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

        experiencePosteService.saveAllFromDTO( annonceDTO.getExperiences(), annonce );

        compatibiliteService.save( annonce );
        return annonce;
    }

    public List<Annonce> findAllDisponible() {
        return annonceRepository.findAnnonceAvailable();
    }
}
