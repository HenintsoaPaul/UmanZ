package mg.itu.rh.service;

import mg.itu.rh.dto.ExperienceTalentDTO;
import mg.itu.rh.entity.ExperienceTalent;
import mg.itu.rh.entity.Talent;
import mg.itu.rh.entity.id.IdExperienceTalent;
import mg.itu.rh.repository.ExperienceTalentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceTalentService {
    private final PosteService posteService;
    private final ExperienceTalentRepository experienceTalentRepository;

    public ExperienceTalentService( PosteService posteService, ExperienceTalentRepository experienceTalentRepository ) {
        this.posteService = posteService;
        this.experienceTalentRepository = experienceTalentRepository;
    }

    public void saveAllFromDTO( List<ExperienceTalentDTO> experienceTalentsDTO, Talent talent ) {
        for ( ExperienceTalentDTO dto : experienceTalentsDTO ) {
            this.saveFromDTO( dto, talent );
        }
    }

    public ExperienceTalent saveFromDTO( ExperienceTalentDTO dto, Talent talent ) {
        ExperienceTalent ct = dto.getExperienceTalent( posteService );
        IdExperienceTalent id = new IdExperienceTalent( talent.getIdTalent(), dto.getPoste().getIdPoste() );
        ct.setId( id );
        return this.save( ct, talent );
    }

    public ExperienceTalent save( ExperienceTalent expT, Talent talent ) {
        expT.setTalent( talent );
        return this.save( expT );
    }

    public ExperienceTalent save( ExperienceTalent experienceTalent ) {
        return this.experienceTalentRepository.save( experienceTalent );
    }
}
