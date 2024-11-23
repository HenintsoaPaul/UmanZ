package mg.itu.rh.service.talent;

import jakarta.transaction.Transactional;
import mg.itu.rh.dto.critere.DiplomeDTO;
import mg.itu.rh.entity.talent.Talent;
import mg.itu.rh.entity.talent.TalentDiplome;
import mg.itu.rh.entity.critere.Diplome;
import mg.itu.rh.entity.id.IdTalentDiplome;
import mg.itu.rh.repository.talent.TalentDiplomeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TalentDiplomeService {
    private final TalentDiplomeRepository talentDiplomeRepository;

    public TalentDiplomeService( TalentDiplomeRepository talentDiplomeRepository ) {
        this.talentDiplomeRepository = talentDiplomeRepository;
    }

    @Transactional
    public TalentDiplome save( TalentDiplome talentDiplome ) {
        return talentDiplomeRepository.save( talentDiplome );
    }

    @Transactional
    public void saveAll(List<DiplomeDTO> diplomes, Talent talent ) {
        for ( DiplomeDTO dto : diplomes ) {
            TalentDiplome talentDiplome = new TalentDiplome();
            talentDiplome.setTalent( talent );
            Diplome d = dto.getDiplome();
            talentDiplome.setDiplome( d );
            talentDiplome.setId( new IdTalentDiplome( talent.getIdTalent(), d.getIdDiplome() ) );
            this.save( talentDiplome );
        }
    }
}
