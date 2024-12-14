package mg.itu.rh.service.recrutement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import mg.itu.rh.dto.recrutement.EntretienCandidatureDTO;
import mg.itu.rh.dto.recrutement.EntretienValidationDTO;
import mg.itu.rh.entity.talent.Talent;
import mg.itu.rh.repository.recrutement.EntretienRepository;
import mg.itu.rh.repository.recrutement.EtatEntretienRepository;
import mg.itu.rh.service.talent.TalentService;
import org.springframework.stereotype.Service;

import mg.itu.rh.entity.recrutement.Entretien;
import mg.itu.rh.entity.recrutement.EtatEntretien;

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

    public Entretien saveCandidature( EntretienCandidatureDTO entretienDTO ) {
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
        entretien.setNote( 0 );
        return this.save( entretien );
    }

    public Entretien valider( EntretienValidationDTO dto ) {
        Entretien entretien;
        if ( dto.getIdEntretien() == null ) {
            // Case: Validation candidature
            entretien = entretienRepository.findCandidatureByAnnonceAndTalent( dto.getIdAnnonce(), dto.getIdTalent() );
        } else {
            // Case: Validation Entretien
            entretien = entretienRepository.findById( dto.getIdEntretien() )
                    .orElseThrow( () -> new RuntimeException( "Entretien not found" ) );
            entretien.setNote( dto.getNote() );
            String motif = dto.getMotif();
            if ( !motif.isEmpty() ) entretien.setMotif( motif );
        }
        EtatEntretien etatEntretienFille = etatEntretienRepository.findById( entretien.getEtatEntretien().getIdEtatEntretien() + 1 )
                .orElseThrow( () -> new RuntimeException( "On ne peut plus le valider" ) );

        LocalDate dateValidation = LocalDate.now();
        Entretien entretienFille = new Entretien();
        entretienFille.setDateCreation( dateValidation );

        return validerEntretien( entretien, entretienFille, etatEntretienFille, dateValidation );
    }

    public Entretien validerEntretienEnAttenteContrat( Long idEntretien ) {
        Entretien entretien = entretienRepository.findById( idEntretien )
                .orElseThrow( () -> new RuntimeException( "Entretien not found" ) );
        EtatEntretien etatEntretienFille = etatEntretienRepository.findById( 7L )
                .orElseThrow( () -> new RuntimeException( "On ne peut plus le valider" ) );

        LocalDate dateValidation = LocalDate.now();
        Entretien entretienFille = new Entretien();
        entretienFille.setDateCreation( dateValidation );
        entretienFille.setDateValidation( dateValidation );

        return validerEntretien( entretien, entretienFille, etatEntretienFille, dateValidation );
    }

    private Entretien validerEntretien(  Entretien entretien,Entretien entretienFille, EtatEntretien etatEntretienFille, LocalDate dateValidation ) {
        entretienFille.setEtatEntretien( etatEntretienFille );
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
            if ( entretien.getDateValidation() == null ) {
                talents.add( entretien.getTalent() );
            }
        }
        return talents;
    }

    public Entretien refuser( EntretienValidationDTO dto ) {
        LocalDate dateValidation = LocalDate.now();

        Entretien entretien;
        if ( dto.getIdEntretien() == null ) {
            // Case: Refus candidature
            entretien = entretienRepository.findCandidatureByAnnonceAndTalent( dto.getIdAnnonce(), dto.getIdTalent() );
        } else {
            // Case: Refus Entretien
            entretien = entretienRepository.findById( dto.getIdEntretien() )
                    .orElseThrow( () -> new RuntimeException( "Entretien not found" ) );
        }

        EtatEntretien etatEntretien = etatEntretienRepository.findById( 1L )
                .orElseThrow( () -> new RuntimeException( "On ne peut plus le valider" ) );

        entretien.setDateValidation( dateValidation );
        entretien.setEtatEntretien( etatEntretien );
        return this.save( entretien );
    }

    public Entretien findById( Long id ) {
        return entretienRepository.findById( id )
                .orElseThrow( () -> new RuntimeException( "Entretien not found" ) );
    }
}
