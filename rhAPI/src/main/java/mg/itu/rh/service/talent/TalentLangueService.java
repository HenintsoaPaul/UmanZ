package mg.itu.rh.service.talent;

import jakarta.transaction.Transactional;
import mg.itu.rh.dto.critere.LangueDTO;
import mg.itu.rh.entity.talent.Talent;
import mg.itu.rh.entity.talent.TalentLangue;
import mg.itu.rh.entity.critere.Langue;
import mg.itu.rh.entity.critere.NiveauLangue;
import mg.itu.rh.entity.id.IdTalentLangue;
import mg.itu.rh.repository.talent.TalentLangueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TalentLangueService {
    private final TalentLangueRepository talentLangueRepository;

    public TalentLangueService( TalentLangueRepository talentLangueRepository ) {
        this.talentLangueRepository = talentLangueRepository;
    }

    @Transactional
    public TalentLangue save( TalentLangue talentLangue ) {
        return talentLangueRepository.save( talentLangue );
    }

    @Transactional
    public void saveAll(List<LangueDTO> langues, Talent talent ) {
        for ( LangueDTO dto : langues ) {
            TalentLangue talentLangue = new TalentLangue();
            Langue l = dto.getLangue();
            talentLangue.setTalent( talent );
            talentLangue.setLangue( l );
            NiveauLangue nl = dto.getNiveauLangue();
            talentLangue.setNiveauLangue( nl );
            talentLangue.setId( new IdTalentLangue( l.getIdLangue(), nl.getIdNiveauLangue(), talent.getIdTalent() ) );
            this.save( talentLangue );
        }
    }
}
