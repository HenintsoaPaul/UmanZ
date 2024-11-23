package mg.itu.rh.service.talent;

import jakarta.transaction.Transactional;
import mg.itu.rh.entity.critere.Competence;
import mg.itu.rh.entity.interne.Contrat;
import mg.itu.rh.entity.talent.TalentCompetence;
import mg.itu.rh.repository.talent.TalentCompetenceRepository;
import org.springframework.stereotype.Service;

@Service
public class TalentCompetenceService {
    private final TalentCompetenceRepository talentCompetenceRepository;

    public TalentCompetenceService( TalentCompetenceRepository talentCompetenceRepository ) {
        this.talentCompetenceRepository = talentCompetenceRepository;
    }

    @Transactional
    public TalentCompetence save( TalentCompetence talentCompetence ) {
        return talentCompetenceRepository.save( talentCompetence );
    }

    public TalentCompetence findByCompetenceAndContrat( Competence competence, Contrat contrat  ) {
        return this.findByIdCompetenceAndIdTalent( competence.getIdCompetence(), contrat.getTalent().getIdTalent() );
    }

    public TalentCompetence findByIdCompetenceAndIdTalent( Long idCompetence, Long idTalent ) {
        return this.talentCompetenceRepository.findByIdCompetenceAndIdTalent( idCompetence, idTalent );
    }
}
