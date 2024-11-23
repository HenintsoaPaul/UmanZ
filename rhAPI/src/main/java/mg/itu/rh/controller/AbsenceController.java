package mg.itu.rh.controller;

import mg.itu.rh.dto.AbsenceDTO;
import mg.itu.rh.entity.Absence;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

@RestController
@RequestMapping("/absences")
public class AbsenceController {

    @Autowired
    private AbsenceService absenceService;

    @GetMapping
    @JsonView( POV.Public.class )
    public List<Absence> getAllAbsences() {
        return absenceService.findAll();
    }

    @PostMapping("/save")
    @JsonView( POV.Public.class )
    public Absence saveAbsence(@RequestBody AbsenceDTO absenceDTO) {
        return absenceService.save(absenceDTO);
    }
}
