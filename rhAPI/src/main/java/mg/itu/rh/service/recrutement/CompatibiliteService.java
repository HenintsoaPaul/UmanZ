package mg.itu.rh.service.recrutement;

import jakarta.transaction.Transactional;
import mg.itu.rh.entity.recrutement.Annonce;
import mg.itu.rh.entity.recrutement.Compatibilite;
import mg.itu.rh.entity.talent.Talent;
import mg.itu.rh.repository.recrutement.AnnonceRepository;
import mg.itu.rh.repository.recrutement.CompatibiliteRepository;
import mg.itu.rh.service.talent.TalentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompatibiliteService {
    private final CompatibiliteRepository compatibiliteRepository;

    private final TalentService talentService;

    private final AnnonceRepository annonceRepository;

    public CompatibiliteService(CompatibiliteRepository compatibiliteRepository, TalentService talentService, AnnonceRepository annonceRepository) {
        this.compatibiliteRepository = compatibiliteRepository;
        this.talentService=talentService;
        this.annonceRepository = annonceRepository;
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
            Compatibilite compatibilite=new Compatibilite(annonce.getPoste(),talents.get(i));
            compatibilites.add(this.save(compatibilite));
        }
        return compatibilites;
    }

    public List<Annonce> findAllDispoByIdTalent(Long idTalent){
        List<Compatibilite> compatibilites=compatibiliteRepository.findAllByIdTalentAnnonce(idTalent);
        List<Annonce> annoncesDispo=annonceRepository.findAnnonceAvailable();
        for (Compatibilite compatibilite:compatibilites) {
            compatibilite.setPourcentageAnnonce();
            for (Annonce annonce:annoncesDispo){
                if(annonce.getPoste().getIdPoste()==compatibilite.getPoste().getIdPoste()){
                    annonce.setPourcentage(compatibilite.getPourcentage());
                }
            }
        }
        return annoncesDispo;
    }
}
