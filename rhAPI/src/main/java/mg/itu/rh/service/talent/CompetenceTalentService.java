package mg.itu.rh.service.talent;

import mg.itu.rh.dto.talent.CompetenceTalentDTO;
import mg.itu.rh.entity.talent.CompetenceTalent;
import mg.itu.rh.entity.talent.Talent;
import mg.itu.rh.entity.id.IdCompetenceTalent;
import mg.itu.rh.repository.talent.CompetenceTalentRepository;
import mg.itu.rh.service.critere.CompetenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetenceTalentService {
    private final CompetenceService competenceService;
    private final CompetenceTalentRepository competenceTalentRepository;

    public CompetenceTalentService( CompetenceService competenceService, CompetenceTalentRepository competenceTalentRepository ) {
        this.competenceService = competenceService;
        this.competenceTalentRepository = competenceTalentRepository;
    }

    public void saveAllFromDTO(List<CompetenceTalentDTO> experienceTalentsDTO, Talent talent ) {
        for ( CompetenceTalentDTO dto : experienceTalentsDTO ) {
            this.saveFromDTO( dto, talent );
        }
    }

    public CompetenceTalent saveFromDTO( CompetenceTalentDTO dto, Talent talent ) {
        CompetenceTalent ct = dto.getCompetenceTalent( competenceService );
        IdCompetenceTalent id = new IdCompetenceTalent( dto.getCompetence().getIdCompetence(), talent.getIdTalent() );
        ct.setId( id );
        return this.save( ct, talent );
    }

    public CompetenceTalent save( CompetenceTalent expT, Talent talent ) {
        expT.setTalent( talent );
        return this.save( expT );
    }

    public CompetenceTalent save( CompetenceTalent experienceTalent ) {
        return this.competenceTalentRepository.save( experienceTalent );
    }
}
