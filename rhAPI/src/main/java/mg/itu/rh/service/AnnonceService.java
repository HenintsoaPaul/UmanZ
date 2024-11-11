package mg.itu.rh.service;

import mg.itu.rh.dto.AnnonceDTO;
import mg.itu.rh.entity.Annonce;
import mg.itu.rh.entity.CompetenceAnnonce;
import mg.itu.rh.entity.ExperiencePoste;
import mg.itu.rh.entity.Poste;
import mg.itu.rh.repository.AnnonceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnonceService {
    @Autowired
    private AnnonceRepository annonceRepository;

    @Autowired
    private PosteService posteService;

    @Autowired
    private CompetenceAnnonceService competenceAnnonceService;

    @Autowired
    private ExperiencePosteService experiencePosteService;

    public List<Annonce> findAnnonceAvailable() {
        return annonceRepository.findAnnonceAvailable();
    }

    public Annonce findAnnonceById( Long id ) {
        return annonceRepository.findById( id ).orElseThrow( () -> new RuntimeException( "Annonce non reconnue" ) );
    }

    public Annonce save( AnnonceDTO annonceDTO ) {
        Annonce annonce = new Annonce();
        annonce.setDateAnnonce( annonceDTO.getDateAnnonce() );
        annonce.setDateExpiration( annonceDTO.getDateExpiration() );
        annonce.setDateRupture( annonceDTO.getDateRupture() );
        annonce.setPoste( posteService.findById( annonceDTO.getIdPoste() ) );

        Annonce an = annonceRepository.save( annonce );
        experiencePosteService.saveAll( annonceDTO.getExperiences(), an );
        competenceAnnonceService.saveAll( annonceDTO.getCompetences(), an );
        return an;
    }
}
