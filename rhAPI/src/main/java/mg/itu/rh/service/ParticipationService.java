package mg.itu.rh.service;

import mg.itu.rh.dto.ParticipationDTO;
import mg.itu.rh.entity.Formation;
import mg.itu.rh.entity.Participation;
import mg.itu.rh.repository.ParticipationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipationService {
    private final ParticipationRepository participationRepository;
    private final ContratService contratService;

    public ParticipationService( ParticipationRepository participationRepository, ContratService contratService ) {
        this.participationRepository = participationRepository;
        this.contratService = contratService;
    }

    public List<Participation> findAll() {
        return participationRepository.findAll();
    }

    public Participation findById( Long id ) {
        return participationRepository.findById( id )
                .orElseThrow( () -> new RuntimeException( "Participation not found" ) );
    }

    public Participation save( Participation participation ) {
        return participationRepository.save( participation );
    }

    public Participation save( ParticipationDTO participationDTO, Formation formation ) {
        Participation participation = participationDTO.getParticipation( contratService );
        participation.setFormation( formation );
        return this.save( participation );
    }

    public List<Participation> findAllByFormation( Long id ) {
        return this.participationRepository.findAllByFormation(id);
    }
}
