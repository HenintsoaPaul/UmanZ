package mg.itu.rh.service;

import mg.itu.rh.entity.Talent;
import mg.itu.rh.repository.TalentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TalentService {
    private final TalentRepository talentRepository;

    public TalentService( TalentRepository talentRepository ) {
        this.talentRepository = talentRepository;
    }

    public Talent findById( Long id ) {
        return talentRepository.findById( id )
                .orElseThrow( () -> new RuntimeException( "Talent not found" ) );
    }

    public List<Talent> findAll(){
        return talentRepository.findAll();
    }

    public List<Talent> findAll(){
        return talentRepository.findAll();
    }

    public Talent findByEmailAndPassword( String email, String password ) {
        return talentRepository.findByEmailAndPassword( email, password ).orElse( null );
    }
}
