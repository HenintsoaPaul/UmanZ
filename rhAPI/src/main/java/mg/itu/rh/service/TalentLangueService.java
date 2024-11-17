package mg.itu.rh.service;

import jakarta.transaction.Transactional;
import mg.itu.rh.dto.LangueDTO;
import mg.itu.rh.entity.Talent;
import mg.itu.rh.entity.TalentLangue;
import mg.itu.rh.entity.Langue;
import mg.itu.rh.entity.NiveauLangue;
import mg.itu.rh.entity.id.IdTalentLangue;
import mg.itu.rh.repository.TalentLangueRepository;
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
    public void saveAll( List<LangueDTO> langues, Talent talent ) {
        for ( LangueDTO dto : langues ) {
            TalentLangue talentLangue = new TalentLangue();
            Langue l = dto.getLangue();
            talentLangue.setTalent( talent );
            talentLangue.setLangue( l );
            NiveauLangue nl = dto.getNiveauLangue();
            talentLangue.setNiveauLangue( nl );
            talentLangue.setId( new IdTalentLangue( talent.getIdTalent(), l.getIdLangue(), nl.getIdNiveauLangue() ) );
            this.save( talentLangue );
        }
    }
}
