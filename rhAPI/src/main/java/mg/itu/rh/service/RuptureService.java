package mg.itu.rh.service;

import mg.itu.rh.dto.RuptureDTO;
import mg.itu.rh.entity.Rupture;
import mg.itu.rh.entity.TypeRupture;
import mg.itu.rh.repository.RuptureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class RuptureService {
    private final RuptureRepository ruptureRepository;
    private final ContratService contratService;
    private final TypeRuptureService typeRuptureService;

    public RuptureService( RuptureRepository ruptureRepository, ContratService contratService, TypeRuptureService typeRuptureService ) {
        this.ruptureRepository = ruptureRepository;
        this.contratService = contratService;
        this.typeRuptureService = typeRuptureService;
    }

    public Rupture save( RuptureDTO ruptureDTO )
            throws Exception {
        Rupture rupture = new Rupture( ruptureDTO );
        rupture.setContrat( contratService.findById( ruptureDTO.getIdContrat() ) );
        TypeRupture tRupture = typeRuptureService.findById( ruptureDTO.getIdTypeRupture() );
        rupture.setTypeRupture( tRupture );
        rupture.setMotif( tRupture.getTypeRupture() );
        rupture.setDateRupture( LocalDate.now() );
        return ruptureRepository.save( rupture );
    }

    public Rupture save( Rupture rupture )
            throws Exception {
        return ruptureRepository.save( rupture );
    }
}
