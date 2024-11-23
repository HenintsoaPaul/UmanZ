package mg.itu.rh.dto.recrutement;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import mg.itu.rh.dto.critere.DiplomeDTO;
import mg.itu.rh.dto.critere.LangueDTO;
import mg.itu.rh.other.POV;

import java.time.LocalDate;
import java.util.List;

@Data
public class AnnonceDTO {
    @JsonView( { POV.Public.class } )
    private Long idPoste;
    @JsonView( { POV.Public.class } )
    private LocalDate dateAnnonce;
    @JsonView( { POV.Public.class } )
    private LocalDate dateExpiration;

    @JsonView( { POV.Public.class } )
    private List<ExperiencePosteDTO> experiences;

    /*@JsonView( { POV.Public.class } )
    private List<CompetencePosteDTO> competences;

    @JsonView( { POV.Public.class } )
    private List<DiplomeDTO> diplomes;
    @JsonView( { POV.Public.class } )
    private List<LangueDTO> langues;*/
}
