package mg.itu.rh.service.interne;

import mg.itu.rh.dto.interne.ContratDTO;
import mg.itu.rh.entity.interne.Contrat;
import mg.itu.rh.entity.interne.TypeContrat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import mg.itu.rh.entity.talent.Talent;
import mg.itu.rh.repository.interne.ContratRepository;
import mg.itu.rh.service.recrutement.EntretienService;
import mg.itu.rh.service.talent.TalentService;
import org.springframework.stereotype.Service;

@Service
public class ContratService {
    private final ContratRepository contratRepository;
    private final TalentService talentService;
    private final TypeContratService typeContratService;
    private final PosteService posteService;
    private final EntretienService entretienService;

    public ContratService( ContratRepository contratRepository, TalentService talentService, TypeContratService typeContratService, PosteService posteService, EntretienService entretienService ) {
        this.contratRepository = contratRepository;
        this.talentService = talentService;
        this.typeContratService = typeContratService;
        this.posteService = posteService;
        this.entretienService = entretienService;
    }

    public Optional<Contrat> findActualContratByIdTalent( Long idTalent ) {
        return contratRepository.findActualContratByIdTalent( idTalent );
    }

    public List<Contrat> findAllContratNow(){
        return contratRepository.findAllContratEnCoursOnDate(LocalDate.now());
    }

    public Contrat findById( Long idContrat ) {
        return contratRepository.findById( idContrat ).orElseThrow( () -> new RuntimeException( "Contrat non reconnue" ) );
    }

    public Contrat save( Contrat contrat ) {
        return contratRepository.save( contrat );
    }

    public Contrat save( ContratDTO contratDTO ) {
        // set etat entretient en attent contrat
        entretienService.validerEntretienEnAttenteContrat( contratDTO.getIdEntretien() );

        Contrat contrat = new Contrat( contratDTO );
        contrat.setTalent( talentService.findById( contratDTO.getIdTalent() ) );
        contrat.setPoste( posteService.findById( contratDTO.getIdPoste() ) );
        TypeContrat tc = typeContratService.findById( contratDTO.getIdTypeContrat() );
        contrat.setTypeContrat( tc );
        contrat.setContrat( tc.getTypeContrat() ); // nom_contrat io ;>
        return this.save( contrat );
    }

    @Transactional
    public FicheDTO findFiche(int annee,int mois,Long idTalent){
        Talent talent=talentService.findById(idTalent);
        LocalDate dateActuel=LocalDate.of(annee,mois+1,1).minusDays(1);
        Contrat contratEmbauche=contratRepository.findContratEmbauche(idTalent).orElseThrow(()->new RuntimeException("Cette personne n'a jamais travaille chez nous"));
        Contrat contratActuel=contratRepository.findContratByDateTalent(dateActuel,idTalent).orElseThrow(()->new RuntimeException("Cette personne n'est pas un employe la date du "+dateActuel));
        return new FicheDTO(talent,contratEmbauche,contratActuel,dateActuel);
    }

    public List<Contrat> findAll() {
        return contratRepository.findAll();
    }

    public List<Contrat> findAllEmpNow() {
        return contratRepository.findAllContratEnCoursOnDate( LocalDate.now() );
    }
}
