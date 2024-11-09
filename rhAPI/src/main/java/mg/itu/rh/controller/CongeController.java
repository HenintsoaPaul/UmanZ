package mg.itu.rh.controller;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.rh.dto.CongeDTO;
import mg.itu.rh.dto.CongeTalentDTO;
import mg.itu.rh.entity.Conge;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.CongeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conge")
public class CongeController {
    @Autowired
    private CongeService congeService;

    @GetMapping
    @JsonView(POV.Full.class)
    public List<CongeTalentDTO> findALl(){
        return  congeService.findAllValide();
    }

    @GetMapping("/non_valide")
    @JsonView(POV.Full.class)
    public List<CongeTalentDTO> findALlNonValide(){
        return  congeService.findAllNonValide();
    }

    @GetMapping("/validate/{idConge}")
    @JsonView(POV.Full.class)
    public Conge validate(@PathVariable("idConge") Long idConge){
        return congeService.validate(idConge);
    }

    @GetMapping("/talent/{id}")
    @JsonView(POV.Public.class)
    public List<Conge> getCongeByTalent(@PathVariable("id") Long id){
        return congeService.findCongeByIdTalent(id);
    }

    /* *
     *   {
     *       "idTalent":5,
     *       "nbJour":3,
     *       "dateDebut":"2024-10-10",
     *       "motif":"Repos kely fa valaka be"
     *   }
     * */
    @JsonView(POV.Public.class)
    @PostMapping
    public Conge save(@RequestBody CongeDTO congeDTO)throws Exception{
        return congeService.save(congeDTO);
    }

}
