package mg.itu.rh.service;

import jakarta.transaction.Transactional;
import mg.itu.rh.dto.DiplomeDTO;
import mg.itu.rh.entity.Annonce;
import mg.itu.rh.entity.AnnonceDiplome;
import mg.itu.rh.entity.Diplome;
import mg.itu.rh.entity.id.IdAnnonceDiplome;
import mg.itu.rh.repository.AnnonceDiplomeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnonceDiplomeService {
    private final AnnonceDiplomeRepository annonceDiplomeRepository;

    public AnnonceDiplomeService( AnnonceDiplomeRepository annonceDiplomeRepository ) {
        this.annonceDiplomeRepository = annonceDiplomeRepository;
    }

    @Transactional
    public AnnonceDiplome save( AnnonceDiplome annonceDiplome ) {
        return annonceDiplomeRepository.save( annonceDiplome );
    }

    @Transactional
    public void saveAll( List<DiplomeDTO> diplomes, Annonce annonce ) {
        for ( DiplomeDTO dto : diplomes ) {
            AnnonceDiplome annonceDiplome = new AnnonceDiplome();
            annonceDiplome.setAnnonce( annonce );
            Diplome d = dto.getDiplome();
            annonceDiplome.setDiplome( d );
            annonceDiplome.setId( new IdAnnonceDiplome( annonce.getIdAnnonce(), d.getIdDiplome() ) );
            this.save( annonceDiplome );
        }
    }
}
