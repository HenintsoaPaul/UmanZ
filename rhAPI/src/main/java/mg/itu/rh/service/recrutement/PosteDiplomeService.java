package mg.itu.rh.service.recrutement;

import jakarta.transaction.Transactional;
import mg.itu.rh.dto.critere.DiplomeDTO;
import mg.itu.rh.entity.recrutement.PosteDiplome;
import mg.itu.rh.entity.critere.Diplome;
import mg.itu.rh.entity.id.IdPosteDiplome;
import mg.itu.rh.entity.interne.Poste;
import mg.itu.rh.repository.recrutement.AnnonceDiplomeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PosteDiplomeService {
    private final AnnonceDiplomeRepository annonceDiplomeRepository;

    public PosteDiplomeService(AnnonceDiplomeRepository annonceDiplomeRepository ) {
        this.annonceDiplomeRepository = annonceDiplomeRepository;
    }

    @Transactional
    public PosteDiplome save(PosteDiplome annonceDiplome ) {
        return annonceDiplomeRepository.save( annonceDiplome );
    }

    @Transactional
    public void saveAll(List<DiplomeDTO> diplomes, Poste poste ) {
        for ( DiplomeDTO dto : diplomes ) {
            PosteDiplome annonceDiplome = new PosteDiplome();
            annonceDiplome.setPoste( poste );
            Diplome d = dto.getDiplome();
            annonceDiplome.setDiplome( d );
            annonceDiplome.setId( new IdPosteDiplome( poste.getIdPoste(), d.getIdDiplome() ) );
            this.save( annonceDiplome );
        }
    }
}
