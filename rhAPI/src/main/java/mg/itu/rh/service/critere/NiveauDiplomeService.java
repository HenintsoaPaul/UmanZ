package mg.itu.rh.service.critere;

import mg.itu.rh.entity.critere.NiveauDiplome;
import mg.itu.rh.repository.critere.NiveauDiplomeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NiveauDiplomeService {
    private final NiveauDiplomeRepository niveauDiplomeRepository;

    public NiveauDiplomeService( NiveauDiplomeRepository niveauDiplomeRepository ) {
        this.niveauDiplomeRepository = niveauDiplomeRepository;
    }

    public NiveauDiplome findById( Long id ) {
        return niveauDiplomeRepository.findById( id ).orElseThrow( () -> new RuntimeException( "Niveau diplome non reconnue" ) );
    }

    public List<NiveauDiplome> findAll() {
        return niveauDiplomeRepository.findAll();
    }
}
