package mg.itu.rh.service;

import mg.itu.rh.dto.CompetenceCibleDTO;
import mg.itu.rh.entity.CompetenceCible;
import mg.itu.rh.entity.Formation;
import mg.itu.rh.entity.id.IdCompetenceCible;
import mg.itu.rh.repository.CompetenceCibleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetenceCibleService {
    private final CompetenceService competenceService;
    private final CompetenceCibleRepository competenceCibleRepository;

    public CompetenceCibleService( CompetenceService competenceService, CompetenceCibleRepository competenceCibleRepository ) {
        this.competenceService = competenceService;
        this.competenceCibleRepository = competenceCibleRepository;
    }

    public void saveAllFromDTO( List<CompetenceCibleDTO> experienceCiblesDTO, Formation formation ) {
        for ( CompetenceCibleDTO dto : experienceCiblesDTO ) {
            this.saveFromDTO( dto, formation );
        }
    }

    public CompetenceCible saveFromDTO( CompetenceCibleDTO dto, Formation formation ) {
        CompetenceCible ct = dto.getCompetenceCible( competenceService );
        IdCompetenceCible id = new IdCompetenceCible( dto.getCompetence().getIdCompetence(), formation.getIdFormation() );
        ct.setId( id );
        return this.save( ct, formation );
    }

    public CompetenceCible save( CompetenceCible expT, Formation formation ) {
        expT.setFormation( formation );
        return this.save( expT );
    }

    public CompetenceCible save( CompetenceCible experienceCible ) {
        return this.competenceCibleRepository.save( experienceCible );
    }
}
