package mg.itu.rh.service;

import jakarta.transaction.Transactional;
import mg.itu.rh.entity.Annonce;
import mg.itu.rh.entity.Compatibilite;
import mg.itu.rh.entity.Talent;
import mg.itu.rh.repository.CompatibiliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
            System.out.println(compatibilite.getPourcentage()+" pourcentage");
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
