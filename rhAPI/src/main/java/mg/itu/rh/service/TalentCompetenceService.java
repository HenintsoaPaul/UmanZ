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
