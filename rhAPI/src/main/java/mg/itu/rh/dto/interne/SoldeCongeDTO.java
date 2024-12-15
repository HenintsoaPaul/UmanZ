package mg.itu.rh.dto.interne;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mg.itu.rh.exception.interne.CongeException;
import mg.itu.rh.other.POV;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SoldeCongeDTO {
    @JsonView( { POV.Conge.class } )
    Long idContrat;
    @JsonView( { POV.Conge.class } )
    int nbPris;
    @JsonView( { POV.Conge.class } )
    double solde;
    @JsonView( { POV.Conge.class } )
    long moisDeService;

    public void controllerDemande( int nbJourDemande )
            throws CongeException {
        if ( solde - nbJourDemande < 0 ) {
            throw new CongeException( "Solde de conge insuffisant. " +
                    "Il vous reste " + nbJourDemande + " jr de conge." );
        }
    }
}
