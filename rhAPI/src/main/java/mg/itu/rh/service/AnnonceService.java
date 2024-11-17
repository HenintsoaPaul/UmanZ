package mg.itu.rh.service;

import jakarta.transaction.Transactional;
import mg.itu.rh.dto.AnnonceDTO;
import mg.itu.rh.dto.AnnonceLangueDTO;
import mg.itu.rh.entity.Annonce;
import mg.itu.rh.entity.AnnonceLangue;
import mg.itu.rh.entity.Compatibilite;
import mg.itu.rh.entity.Diplome;
import mg.itu.rh.repository.AnnonceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnnonceService {
    private final AnnonceRepository annonceRepository;

    private final PosteService posteService;

    private final CompetenceAnnonceService competenceAnnonceService;

    private final ExperiencePosteService experiencePosteService;

    private final CompatibiliteService compatibiliteService;

    private final DiplomeService diplomeService;

    private final LangueService langueService;

    private final NiveauLangueService niveauLangueService;

    private final AnnonceLangueService annonceLangueService;

    public AnnonceService(AnnonceRepository annonceRepository, PosteService posteService, CompetenceAnnonceService competenceAnnonceService, ExperiencePosteService experiencePosteService, CompatibiliteService compatibiliteService, DiplomeService diplomeService, LangueService langueService, NiveauLangueService niveauLangueService, AnnonceLangueService annonceLangueService) {
        this.annonceRepository = annonceRepository;
        this.posteService = posteService;
        this.competenceAnnonceService = competenceAnnonceService;
        this.experiencePosteService = experiencePosteService;
        this.compatibiliteService = compatibiliteService;
        this.diplomeService = diplomeService;
        this.langueService = langueService;
        this.niveauLangueService = niveauLangueService;
        this.annonceLangueService = annonceLangueService;
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
        this.addDiplome(annonce,annonceDTO.getIdDiplomes());
        this.addLangue(annonce,annonceDTO.getLangues());

        annonce = annonceRepository.save( annonce );
        // Save into tables liaisons
        experiencePosteService.saveAllFromDTO( annonceDTO.getExperiences(), annonce );
        competenceAnnonceService.saveAllFromDTO( annonceDTO.getCompetences(), annonce );
        compatibiliteService.save(annonce);
        return annonce;
    }

    @Transactional
    public void addDiplome(Annonce annonce,List<Long> idDiplomes){
        for (int i = 0; i < idDiplomes.size(); i++) {
            Diplome diplome=diplomeService.findByIdDiplome(idDiplomes.get(i));
            annonce.addDiplome(diplome);
        }
    }

    @Transactional
    public void addLangue(Annonce annonce, List<AnnonceLangueDTO> annonceLangues){
        annonce.setAnnonceLangues(new ArrayList<>());
        for (AnnonceLangueDTO annonceLangue: annonceLangues) {
            AnnonceLangue langueAnnonce=new AnnonceLangue();
            langueAnnonce.setAnnonce(annonce);
            langueAnnonce.setLangue(langueService.findById(annonceLangue.getIdLangue()));
            langueAnnonce.setNiveauLangue(niveauLangueService.findById(annonceLangue.getIdLangue()));
            annonce.getAnnonceLangues().add(langueAnnonce);
        }
    }

    public List<Annonce> findAllDisponible() {
        return annonceRepository.findAnnonceAvailable();
    }
}
