package mg.itu.rh.service.interne;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import mg.itu.rh.dto.interne.AbsenceDTO;
import mg.itu.rh.entity.interne.Absence;
import mg.itu.rh.entity.interne.Contrat;
import mg.itu.rh.repository.interne.AbsenceRepository;

@RequiredArgsConstructor
@Service
public class AbsenceService {
    private final AbsenceRepository absenceRepository;
    private final ContratService contratService;

    public List<Absence> findAll() {
        return absenceRepository.findAll();
    }

    public long countByContratAndMoisAndAnnee(Long idContrat, int mois, int annee) {
        return absenceRepository.countByContratAndMoisAndAnnee(idContrat, mois, annee);
    }

    public Absence save(AbsenceDTO absenceDTO) {
        Absence absence = new Absence();
        absence.setMotif(absenceDTO.getMotif());
        absence.setDateAbsence(absenceDTO.getDateAbsence());

        Long idTalent = absenceDTO.getIdTalent();
        Contrat contrat = contratService.findActualContratByIdTalent(idTalent);
        absence.setContrat(contrat);

        // Contrat contrat = contratService.findById(absenceDTO.getIdContrat());
        // absence.setContrat(contrat);

        return absenceRepository.save(absence);
    }
}
