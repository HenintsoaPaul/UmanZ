package mg.itu.rh.service;

import mg.itu.rh.entity.Absence;
import mg.itu.rh.dto.AbsenceDTO;
import mg.itu.rh.entity.Contrat;
import mg.itu.rh.repository.AbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        Contrat contrat = contratService.findById(absenceDTO.getIdContrat());
        absence.setContrat(contrat);

        return absenceRepository.save(absence);
    }
}
