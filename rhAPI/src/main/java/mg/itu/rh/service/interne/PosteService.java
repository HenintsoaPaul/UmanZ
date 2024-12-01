package mg.itu.rh.service.interne;

import mg.itu.rh.dto.poste.PosteDTO;
import mg.itu.rh.entity.interne.Poste;
import mg.itu.rh.repository.PosteRepository;
import mg.itu.rh.service.recrutement.PosteDiplomeService;
import mg.itu.rh.service.recrutement.CompetencePosteService;
import mg.itu.rh.service.recrutement.PosteLangueService;
import org.springframework.stereotype.Service;

@Service
public class PosteService {
    private final PosteRepository posteRepository;
    private final CompetencePosteService competencePosteService;
    private final PosteLangueService annonceLangueService;
    private final PosteDiplomeService posteDiplomeService;

    public PosteService(PosteRepository posteRepository, CompetencePosteService competencePosteService, PosteLangueService annonceLangueService, PosteDiplomeService annonceDiplomeService) {
        this.posteRepository = posteRepository;
        this.competencePosteService = competencePosteService;
        this.annonceLangueService = annonceLangueService;
        this.posteDiplomeService = annonceDiplomeService;
    }

    public Poste findById( Long id ) {
        return posteRepository.findById( id )
                .orElseThrow( () -> new RuntimeException( "Poste non reconnue" ) );
    }

//    public List<Poste> findAll() {
//        return posteRepository.findAllPostes();
//    }

    public Poste save(Poste poste){
        return posteRepository.save(poste);
    }

    public Poste save(PosteDTO posteDTO){
        Poste poste=new Poste(posteDTO);
        poste=this.save(poste);

        competencePosteService.saveAllFromDTO( posteDTO.getCompetences(), poste );
        posteDiplomeService.saveAll( posteDTO.getDiplomes(), poste );
        annonceLangueService.saveAll( posteDTO.getLangues(), poste);

        return poste;
    }
}
