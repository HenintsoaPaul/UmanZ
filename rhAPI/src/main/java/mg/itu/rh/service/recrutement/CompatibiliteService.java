package mg.itu.rh.service.recrutement;

import jakarta.transaction.Transactional;
import mg.itu.rh.entity.Poste;
import mg.itu.rh.entity.recrutement.Annonce;
import mg.itu.rh.entity.recrutement.Compatibilite;
import mg.itu.rh.entity.talent.Talent;
import mg.itu.rh.repository.recrutement.CompatibiliteRepository;
import mg.itu.rh.service.talent.TalentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompatibiliteService {
    private final CompatibiliteRepository compatibiliteRepository;

    private final TalentService talentService;

    public CompatibiliteService(CompatibiliteRepository compatibiliteRepository,TalentService talentService) {
        this.compatibiliteRepository = compatibiliteRepository;
        this.talentService=talentService;
    }

    @Transactional
    public Compatibilite save(Compatibilite compatibilite){
        return compatibiliteRepository.save(compatibilite);
    }

    @Transactional
    public List<Compatibilite> save(Annonce annonce){
        List<Talent> talents=talentService.findAll();
        List<Compatibilite> compatibilites=new ArrayList<Compatibilite>();
        for(int i=0;i<talents.size();i++){
            Compatibilite compatibilite=new Compatibilite(annonce,talents.get(i));
            compatibilites.add(this.save(compatibilite));
        }
        return compatibilites;
    }

    public List<Annonce> findAllDispoByIdTalent(Long idTalent){
        List<Compatibilite> compatibilites=compatibiliteRepository.findAllByIdTalentAnnonce(idTalent);
        List<Annonce> annonces=new ArrayList<Annonce>();
        for (Compatibilite compatibilite:compatibilites) {
            compatibilite.setPourcentageAnnonce();
            annonces.add(compatibilite.getAnnonce());
        }
        return annonces;
    }
}
