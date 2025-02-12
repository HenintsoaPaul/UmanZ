package mg.itu.rh.service.critere;

import mg.itu.rh.entity.critere.Diplome;
import mg.itu.rh.repository.critere.DiplomeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiplomeService {
    private final DiplomeRepository diplomeRepository;

    public DiplomeService( DiplomeRepository diplomeRepository ) {
        this.diplomeRepository = diplomeRepository;
    }

    public Diplome findByIdDiplome( Long id )
            throws RuntimeException {
        return diplomeRepository.findById( id ).orElseThrow( () -> new RuntimeException( "Diplome non reconnue" ) );
    }

    public List<Diplome> findAll() {
        return diplomeRepository.findAll();
    }

    public void saveAll( List<Diplome> diplomes ) {
        diplomeRepository.saveAll( diplomes );
    }
}
