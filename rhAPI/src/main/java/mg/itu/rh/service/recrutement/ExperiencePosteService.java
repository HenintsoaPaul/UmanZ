package mg.itu.rh.service.recrutement;

import jakarta.transaction.Transactional;
import mg.itu.rh.dto.recrutement.ExperiencePosteDTO;
import mg.itu.rh.entity.recrutement.Annonce;
import mg.itu.rh.entity.recrutement.ExperiencePoste;
import mg.itu.rh.entity.id.IdExperiencePoste;
import mg.itu.rh.repository.recrutement.ExperiencePosteRepository;
import mg.itu.rh.service.interne.PosteService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperiencePosteService {
    private final PosteService posteService;
    private final ExperiencePosteRepository experiencePosteRepository;

    public ExperiencePosteService( PosteService posteService, ExperiencePosteRepository experiencePosteRepository ) {
        this.posteService = posteService;
        this.experiencePosteRepository = experiencePosteRepository;
    }

    public List<ExperiencePoste> findAll() {
        return experiencePosteRepository.findAll();
    }

    @Transactional
    public void saveAllFromDTO(List<ExperiencePosteDTO> experiencePostesDTO, Annonce annonce ) {
        for ( ExperiencePosteDTO dto : experiencePostesDTO )
            annonce.addExperiencePoste(save( dto, annonce ));
    }

    @Transactional
    public ExperiencePoste save( ExperiencePosteDTO dto, Annonce annonce ) {
        IdExperiencePoste id = new IdExperiencePoste(annonce.getIdAnnonce(), dto.getPoste().getIdPoste() );
        ExperiencePoste expPoste = dto.getExperiencePoste( posteService );
        expPoste.setId( id );
        return this.save( expPoste, annonce );
    }

    public ExperiencePoste save( ExperiencePoste experiencePoste, Annonce annonce ) {
        experiencePoste.setAnnonce( annonce );
        return experiencePosteRepository.save( experiencePoste );
    }

    public List<ExperiencePoste> findAllByIdAnnonce( Long idAnnonce ) {
        return experiencePosteRepository.findAllByIdAnnonce( idAnnonce );
    }
}
