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
}
