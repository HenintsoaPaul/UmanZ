package mg.itu.rh.controller.interne;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.rh.dto.interne.RuptureDTO;
import mg.itu.rh.entity.interne.Rupture;
import mg.itu.rh.other.POV;
import mg.itu.rh.service.interne.RuptureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rupture")
public class RuptureController {
    @Autowired
    private RuptureService ruptureService;

    /*
    * {
    *       "idContrat":5,
    *       "idTypeRupture":4
    * }
    * */
    @PostMapping
    @JsonView(POV.Public.class)
    public Rupture save(@RequestBody RuptureDTO ruptureDTO)throws Exception{
        return ruptureService.save(ruptureDTO);
    }
}
