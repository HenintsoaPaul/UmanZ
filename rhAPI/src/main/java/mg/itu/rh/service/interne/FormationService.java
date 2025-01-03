package mg.itu.rh.service.interne;

import mg.itu.rh.controller.evaluation.ResultatController;
import mg.itu.rh.dto.interne.FormationDTO;
import mg.itu.rh.entity.interne.CompetenceCible;
import mg.itu.rh.entity.interne.Formation;
import mg.itu.rh.entity.interne.Participation;
import mg.itu.rh.entity.talent.TalentCompetence;
import mg.itu.rh.repository.interne.FormationRepository;
import mg.itu.rh.service.talent.TalentCompetenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormationService {
    private final FormationRepository formationRepository;
    private final CompetenceCibleService competenceCibleService;
    private final ParticipationService participationService;
    private final TalentCompetenceService talentCompetenceService;

    public FormationService( FormationRepository formationRepository, CompetenceCibleService competenceCibleService, ParticipationService participationService, TalentCompetenceService talentCompetenceService, ResultatController resultatTechniqueController ) {
        this.formationRepository = formationRepository;
        this.competenceCibleService = competenceCibleService;
        this.participationService = participationService;
        this.talentCompetenceService = talentCompetenceService;
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
        formation.setEstFini( false );

        formation = this.formationRepository.save( formation );
        // Save into tables liaisons
        competenceCibleService.saveAllFromDTO( formationDTO.getCompetences(), formation );
        return formation;
    }

    public Formation finir( Long id ) {
        Formation f = this.findById( id );
        for ( Participation part : participationService.findAllByFormation( f.getIdFormation() ) ) {
            for ( CompetenceCible cpc : f.getCompetenceCibles() ) {
                TalentCompetence talentCompetence = talentCompetenceService.findByCompetenceAndContrat( cpc.getCompetence(), part.getContrat() );
                int pt = talentCompetence.getPoint() + cpc.getPointGagne();
                talentCompetence.setPoint( pt );
                talentCompetenceService.save( talentCompetence );
            }
        }
        f.setEstFini( true );
        return this.formationRepository.save( f );
    }

    public List<Formation> findAll() {
        return this.formationRepository.findAll();
    }
}
