package mg.itu.rh.dto.poste;

import lombok.Data;
import mg.itu.rh.dto.critere.DiplomeDTO;
import mg.itu.rh.dto.critere.LangueDTO;

import java.util.List;

@Data
public class PosteDTO {
    String poste;
    String description;
    Long idTypePoste;
    List<LangueDTO> langues;
    List<DiplomeDTO> diplomes;
    List<CompetencePosteDTO> competences;
}
