package mg.itu.rh.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import mg.itu.rh.dto.EntretienCandidatureDTO;
import mg.itu.rh.dto.EntretienValidationDTO;
import mg.itu.rh.entity.Talent;
import org.springframework.stereotype.Service;

import mg.itu.rh.entity.Entretien;
import mg.itu.rh.entity.EtatEntretien;
import mg.itu.rh.repository.EntretienRepository;
import mg.itu.rh.repository.EtatEntretienRepository;

@Service
public class EntretienService {
    private final EntretienRepository entretienRepository;

    private final AnnonceService annonceService;

    private final TalentService talentService;

    private final EtatEntretienRepository etatEntretienRepository;

    public EntretienService( EntretienRepository entretienRepository, AnnonceService annonceService, TalentService talentService, EtatEntretienRepository etatEntretienRepository ) {
        this.entretienRepository = entretienRepository;
        this.annonceService = annonceService;
        this.talentService = talentService;
        this.etatEntretienRepository = etatEntretienRepository;
    }

    public Entretien save( Entretien entretien ) {
        return entretienRepository.save( entretien );
    }

    public Entretien saveCandidat( EntretienCandidatureDTO entretienDTO ) {
        Entretien entretien = new Entretien( entretienDTO );
        entretien.setTalent( talentService.findById( entretienDTO.getIdTalent() ) );
        entretien.setAnnonce( annonceService.findById( entretienDTO.getIdAnnonce() ) );
        entretien.setMotif( entretienDTO.getMotif() );
        entretien.setEtatEntretien( etatEntretienRepository.findByNiveau( 2 ) );
        return this.save( entretien );
    }

    public Entretien save( EntretienCandidatureDTO entretienDTO )
            throws Exception {
        Entretien entretien = new Entretien( entretienDTO );
        entretien.setTalent( talentService.findById( entretienDTO.getIdTalent() ) );
        entretien.setAnnonce( annonceService.findById( entretienDTO.getIdAnnonce() ) );
        entretien.setMotif( entretienDTO.getMotif() );
        entretien.setEtatEntretien( etatEntretienRepository.findByNiveau( entretienDTO.getNiveau() ) );
        return this.save( entretien );
    }

    public Entretien valider( EntretienValidationDTO dto ) {
        LocalDate dateValidation = LocalDate.now();

        Entretien entretien = entretienRepository.findCandidatureByAnnonceAndTalent( dto.getIdAnnonce(), dto.getIdTalent() );

        EtatEntretien etatEntretien = etatEntretienRepository.findById( entretien.getEtatEntretien().getIdEtatEntretien() + 1 )
                .orElseThrow( () -> new RuntimeException( "On ne peut plus le valider" ) );

        Entretien entretienFille = new Entretien();
        entretienFille.setDateCreation( dateValidation );
        entretienFille.setEtatEntretien( etatEntretien );
        entretienFille.setTalent( entretien.getTalent() );
        entretienFille.setAnnonce( entretien.getAnnonce() );

        this.save( entretienFille );

        entretien.setEnfant( entretienFille );
        entretien.setDateValidation( dateValidation );
        return this.save( entretien );
    }

    public List<Entretien> findAll() {
        return entretienRepository.findAll();
    }

    public List<Entretien> findByEtat( Long idEtat ) {
        EtatEntretien etatEntretien = etatEntretienRepository.findById( idEtat ).orElseThrow( () -> new RuntimeException( "Etat non trouve" ) );
        return entretienRepository.findByEtatEntretien( etatEntretien );
    }

    public List<Entretien> getCandidaList() {
        return entretienRepository.findAllWhereEnfantIsNull();
    }

    public List<Entretien> findAllByIdAnnonce( Long idAnnonce ) {
        Long etatCandidature = 2L;
        return entretienRepository.findAllByIdAnnonceAndEtat( idAnnonce, etatCandidature );
    }

    public List<Talent> findAllCandidatsOfAnnonce( Long idAnnonce ) {
        List<Talent> talents = new ArrayList<>();
        for ( Entretien entretien : this.findAllByIdAnnonce( idAnnonce ) ) {
            talents.add( entretien.getTalent() );
        }
        return talents;
    }
}
