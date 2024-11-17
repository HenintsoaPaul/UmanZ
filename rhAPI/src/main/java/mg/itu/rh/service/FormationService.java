package mg.itu.rh.service;

import mg.itu.rh.dto.FormationDTO;
import mg.itu.rh.entity.Formation;
import mg.itu.rh.repository.FormationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormationService {
    private final FormationRepository formationRepository;
    private final CompetenceCibleService competenceCibleService;

    public FormationService( FormationRepository formationRepository, CompetenceCibleService competenceCibleService ) {
        this.formationRepository = formationRepository;
        this.competenceCibleService = competenceCibleService;
    }

    public List<Formation> findAllDisponible() {
        return formationRepository.findAllDisponible();
    }

    public Formation findById( Long idFormation ) {
        return formationRepository.findById( idFormation )
                .orElseThrow( () -> new RuntimeException( "Formation with id " + idFormation + " not found" ) );
    }

    public Formation save( FormationDTO formationDTO ) {
        Formation formation = new Formation();
        formation.setNomFormation( formationDTO.getNomFormation() );
        formation.setDateDebut( formationDTO.getDateDebut() );
        formation.setDateFin( formationDTO.getDateFin() );

        formation = this.formationRepository.save( formation );
        // Save into tables liaisons
        competenceCibleService.saveAllFromDTO( formationDTO.getCompetences(), formation );
        return formation;
    }
}
