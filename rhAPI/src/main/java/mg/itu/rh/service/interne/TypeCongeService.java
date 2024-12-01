package mg.itu.rh.service.interne;

import mg.itu.rh.entity.interne.TypeConge;
import mg.itu.rh.repository.interne.TypeCongeRepository;
import org.springframework.stereotype.Service;

@Service
public class TypeCongeService {
    private final TypeCongeRepository typeCongeRepository;

    public TypeCongeService( TypeCongeRepository typeCongeRepository ) {
        this.typeCongeRepository = typeCongeRepository;
    }

    public TypeConge findById( Long id ) {
        return typeCongeRepository.findById( id )
                .orElseThrow( () -> new RuntimeException( "Type de conge non reconnue" ) );
    }
}
