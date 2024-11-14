package mg.itu.rh.dto;

import lombok.Data;
import mg.itu.rh.entity.Participation;
import mg.itu.rh.entity.id.IdParticipation;
import mg.itu.rh.service.ContratService;
import mg.itu.rh.service.FormationService;

import java.time.LocalDate;

@Data
public class ParticipationDTO {
    private Long idFormation;
    private Long idContrat;

    public Participation getParticipation( ContratService contratService, FormationService formationService ) {
        Participation p = new Participation();
        p.setContrat( contratService.findById( this.getIdContrat() ) );
        p.setFormation( formationService.findById( this.getIdFormation() ) );
        p.setDateInscription( LocalDate.now() );
        IdParticipation id = new IdParticipation( this.getIdContrat(), this.getIdFormation() );
        p.setId( id );
        return p;
    }
}
