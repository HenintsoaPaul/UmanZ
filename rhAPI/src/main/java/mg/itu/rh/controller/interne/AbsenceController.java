package mg.itu.rh.controller.interne;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import mg.itu.rh.dto.interne.AbsenceDTO;
import mg.itu.rh.entity.interne.Absence;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.interne.AbsenceService;

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
