package mg.itu.rh.service.interne;

import mg.itu.rh.constante.ConstanteRupture;
import mg.itu.rh.dto.interne.RuptureDTO;
import mg.itu.rh.dto.rupture.DemissionRequest;
import mg.itu.rh.dto.rupture.RenvoiRequest;
import mg.itu.rh.dto.rupture.RetraiteRequest;
import mg.itu.rh.entity.interne.Rupture;
import mg.itu.rh.repository.interne.RuptureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Long idTypeRupture = ruptureDTO.getIdTypeRupture();

        Rupture rupture = new Rupture( ruptureDTO );
        rupture.setContrat( contratService.findById( ruptureDTO.getIdContrat() ) );
        rupture.setTypeRupture( typeRuptureService.findById( idTypeRupture ) );

        return ruptureRepository.save( rupture );
    }

    public Rupture save( Rupture rupture )
            throws Exception {
        return ruptureRepository.save( rupture );
    }

    public Rupture saveDemission( DemissionRequest demissionRequest ) {
        Long idTypeRupture = ConstanteRupture.idTypeDemission;

        Rupture rupture = new Rupture( demissionRequest );
        rupture.setContrat( contratService.findById( demissionRequest.getIdContrat() ) );
        rupture.setTypeRupture( typeRuptureService.findById( idTypeRupture ) );

        return ruptureRepository.save( rupture );
    }

    public Rupture saveRetraite( RetraiteRequest retraiteRequest ) {
        Long idTypeRupture = ConstanteRupture.idTypeRetraite;

        Rupture rupture = new Rupture( retraiteRequest );
        rupture.setContrat( contratService.findById( retraiteRequest.getIdContrat() ) );
        rupture.setTypeRupture( typeRuptureService.findById( idTypeRupture ) );

        return ruptureRepository.save( rupture );
    }

    public Rupture saveRenvoi( RenvoiRequest renvoiRequest ) {
        Long idTypeRupture = ConstanteRupture.idTypeLicenciement;

        Rupture rupture = new Rupture( renvoiRequest );
        rupture.setContrat( contratService.findById( renvoiRequest.getIdContrat() ) );
        rupture.setTypeRupture( typeRuptureService.findById( idTypeRupture ) );

        return ruptureRepository.save( rupture );
    }

    public List<Rupture> findAllPendingRuptures() {
        return this.ruptureRepository.findAllPendingRuptures();
    }

    public Rupture findAllPendingRupturesOfEmp( Long idContrat ) {
        return this.ruptureRepository.findAllPendingRupturesOfEmp( idContrat );
    }
}
