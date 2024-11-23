package mg.itu.rh.service.recrutement;

import jakarta.transaction.Transactional;
import mg.itu.rh.dto.critere.DiplomeDTO;
import mg.itu.rh.entity.recrutement.Annonce;
import mg.itu.rh.entity.recrutement.AnnonceDiplome;
import mg.itu.rh.entity.critere.Diplome;
import mg.itu.rh.entity.id.IdAnnonceDiplome;
import mg.itu.rh.repository.recrutement.AnnonceDiplomeRepository;
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
    public void saveAll(List<DiplomeDTO> diplomes, Annonce annonce ) {
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
