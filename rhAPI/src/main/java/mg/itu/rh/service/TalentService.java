package mg.itu.rh.service;

import mg.itu.rh.dto.TalentDTO;
import mg.itu.rh.entity.Talent;
import mg.itu.rh.repository.TalentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TalentService {
    private final TalentRepository talentRepository;
    private final ExperienceTalentService experienceTalentService;
    private final CompetenceTalentService competenceTalentService;

    public TalentService( TalentRepository talentRepository, ExperienceTalentService experienceTalentService, CompetenceTalentService competenceTalentService ) {
        this.talentRepository = talentRepository;
        this.experienceTalentService = experienceTalentService;
        this.competenceTalentService = competenceTalentService;
    }

    public Talent findById( Long id ) {
        return talentRepository.findById( id )
                .orElseThrow( () -> new RuntimeException( "Talent not found" ) );
    }

    public List<Talent> findAll(){
        return talentRepository.findAll();
    }

    public Talent findByEmailAndPassword( String email, String password ) {
        return talentRepository.findByEmailAndPassword( email, password ).orElse( null );
    }

    public Talent save( TalentDTO talentDTO ) {
        Talent t = new Talent( talentDTO );
        t = this.save( t );
        // set liaisons
        experienceTalentService.saveAllFromDTO( talentDTO.getExperiences(), t );
        competenceTalentService.saveAllFromDTO( talentDTO.getCompetences(), t );
        return t;
    }

    public Talent save( Talent talent ) {
        return this.talentRepository.save( talent );
    }
}
