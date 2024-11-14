package mg.itu.rh.service;

import mg.itu.rh.entity.Formation;
import mg.itu.rh.repository.FormationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormationService {
    private final FormationRepository formationRepository;

    public FormationService( FormationRepository formationRepository ) {
        this.formationRepository = formationRepository;
    }

    public List<Formation> findAllDisponible() {
        return formationRepository.findAllDisponible();
    }

    public Formation findById( Long idFormation ) {
        return formationRepository.findById( idFormation )
                .orElseThrow( () -> new RuntimeException( "Formation with id " + idFormation + " not found" ) );
    }
}
