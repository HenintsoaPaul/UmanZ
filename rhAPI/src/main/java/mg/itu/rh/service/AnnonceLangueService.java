package mg.itu.rh.service;

import jakarta.transaction.Transactional;
import mg.itu.rh.dto.LangueDTO;
import mg.itu.rh.entity.Annonce;
import mg.itu.rh.entity.AnnonceLangue;
import mg.itu.rh.entity.Langue;
import mg.itu.rh.entity.NiveauLangue;
import mg.itu.rh.entity.id.IdAnnonceLangue;
import mg.itu.rh.repository.AnnonceLangueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnonceLangueService {
    private final AnnonceLangueRepository annonceLangueRepository;

    public AnnonceLangueService( AnnonceLangueRepository annonceLangueRepository ) {
        this.annonceLangueRepository = annonceLangueRepository;
    }

    @Transactional
    public AnnonceLangue save( AnnonceLangue annonceLangue ) {
        return annonceLangueRepository.save( annonceLangue );
    }

    @Transactional
    public void saveAll( List<LangueDTO> langues, Annonce annonce ) {
        for ( LangueDTO dto : langues ) {
            AnnonceLangue annonceLangue = new AnnonceLangue();
            Langue l = dto.getLangue();
            annonceLangue.setAnnonce( annonce );
            annonceLangue.setLangue( l );
            NiveauLangue nl = dto.getNiveauLangue();
            annonceLangue.setNiveauLangue( nl );
            annonceLangue.setId( new IdAnnonceLangue( annonce.getIdAnnonce(), l.getIdLangue(), nl.getIdNiveauLangue() ) );
            this.save( annonceLangue );
        }
    }
}
