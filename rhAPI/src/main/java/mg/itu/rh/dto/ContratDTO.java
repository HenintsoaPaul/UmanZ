package mg.itu.rh.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import mg.itu.rh.other.POV;

import java.time.LocalDate;

@Data
public class ContratDTO {
    @JsonView(POV.Public.class)
    String contrat;
    @JsonView(POV.Public.class)
    LocalDate dateDebut;
    @JsonView(POV.Public.class)
    LocalDate dateFin;
    @JsonView(POV.Public.class)
    double salaireHoraire;
    @JsonView(POV.Public.class)
    int nbJourSemaine;
    @JsonView(POV.Public.class)
    int nbJourCongeAn;
    @JsonView(POV.Public.class)
    int nbHeureJour;
    @JsonView(POV.Public.class)
    Long idPoste;
    @JsonView(POV.Public.class)
    Long idTalent;
    @JsonView(POV.Public.class)
    Long idTypeContrat;
}
