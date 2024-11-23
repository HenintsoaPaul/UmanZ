package mg.itu.rh.service.recrutement;

import jakarta.transaction.Transactional;
import mg.itu.rh.dto.critere.LangueDTO;
import mg.itu.rh.entity.recrutement.Annonce;
import mg.itu.rh.entity.recrutement.AnnonceLangue;
import mg.itu.rh.entity.critere.Langue;
import mg.itu.rh.entity.critere.NiveauLangue;
import mg.itu.rh.entity.id.IdAnnonceLangue;
import mg.itu.rh.repository.recrutement.AnnonceLangueRepository;
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
    public void saveAll(List<LangueDTO> langues, Annonce annonce ) {
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
