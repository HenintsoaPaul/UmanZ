package mg.itu.rh.service;

import mg.itu.rh.dto.RuptureDTO;
import mg.itu.rh.entity.Rupture;
import mg.itu.rh.repository.RuptureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuptureService {
    @Autowired
    private RuptureRepository ruptureRepository;

    @Autowired
    private ContratService contratService;

    @Autowired
    private TypeRuptureService typeRuptureService;

    public Rupture save(RuptureDTO ruptureDTO)throws Exception{
        Rupture rupture=new Rupture(ruptureDTO);
        rupture.setContrat(contratService.findById(ruptureDTO.getIdContrat()));
        rupture.setTypeRupture(typeRuptureService.findById(ruptureDTO.getIdTypeRupture()));
        return ruptureRepository.save(rupture);
    }

    public Rupture save(Rupture rupture) throws Exception{
        return ruptureRepository.save(rupture);
    }
}
