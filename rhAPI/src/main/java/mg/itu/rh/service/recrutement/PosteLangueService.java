package mg.itu.rh.service.recrutement;

import jakarta.transaction.Transactional;
import mg.itu.rh.dto.critere.LangueDTO;
import mg.itu.rh.entity.recrutement.PosteLangue;
import mg.itu.rh.entity.critere.Langue;
import mg.itu.rh.entity.critere.NiveauLangue;
import mg.itu.rh.entity.id.IdPosteLangue;
import mg.itu.rh.entity.interne.Poste;
import mg.itu.rh.repository.recrutement.AnnonceLangueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PosteLangueService {
    private final AnnonceLangueRepository annonceLangueRepository;

    public PosteLangueService(AnnonceLangueRepository annonceLangueRepository ) {
        this.annonceLangueRepository = annonceLangueRepository;
    }

    @Transactional
    public PosteLangue save(PosteLangue annonceLangue ) {
        return annonceLangueRepository.save( annonceLangue );
    }

    @Transactional
    public void saveAll(List<LangueDTO> langues, Poste poste ) {
        for ( LangueDTO dto : langues ) {
            PosteLangue annonceLangue = new PosteLangue();
            Langue l = dto.getLangue();
            annonceLangue.setPoste( poste );
            annonceLangue.setLangue( l );
            NiveauLangue nl = dto.getNiveauLangue();
            annonceLangue.setNiveauLangue( nl );
            annonceLangue.setId( new IdPosteLangue( poste.getIdPoste(), l.getIdLangue(), nl.getIdNiveauLangue() ) );
            this.save( annonceLangue );
        }
    }
}
