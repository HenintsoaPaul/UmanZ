package mg.itu.rh.service;

import jakarta.transaction.Transactional;
import mg.itu.rh.entity.Competence;
import mg.itu.rh.entity.Contrat;
import mg.itu.rh.entity.TalentCompetence;
import mg.itu.rh.repository.TalentCompetenceRepository;
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
package mg.itu.rh.service;

import mg.itu.rh.repository.*;
import mg.itu.rh.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TalentCompetenceService {

    @Autowired
    private TalentCompetenceRepository talentCompetenceRepository;

    public List<TalentCompetence> getCompetencesByTalentId(Long talentId) {
        return talentCompetenceRepository.findByTalentId(talentId);
    }

    // public Optional<TalentCompetence> getCompetenceByIdTalent(Long talentId, Long competenceId) {
    //     return talentCompetenceRepository.findByTalentIdAndCompetenceId(talentId, competenceId);
    // }
}
