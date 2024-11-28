package mg.itu.rh.service.interne;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mg.itu.rh.dto.interne.AbsenceDTO;
import mg.itu.rh.entity.interne.Absence;
import mg.itu.rh.entity.interne.Contrat;
import mg.itu.rh.repository.interne.AbsenceRepository;

@Service
public class AbsenceService {
    @Autowired
    private AbsenceRepository absenceRepository;

    @Autowired
    private ContratService contratService; 

    public List<Absence> findAll() {
        return absenceRepository.findAllAbsences();
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
