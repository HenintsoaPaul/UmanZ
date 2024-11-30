package mg.itu.rh.dto.interne;

import lombok.Data;
import mg.itu.rh.entity.interne.Participation;
import mg.itu.rh.entity.id.IdParticipation;
import mg.itu.rh.service.interne.ContratService;

import java.time.LocalDate;

@Data
public class ParticipationDTO {
    private Long idFormation;
    private Long idContrat;

    public Participation getParticipation( ContratService contratService ) {
        Participation p = new Participation();
        p.setContrat( contratService.findById( this.getIdContrat() ) );
        p.setDateInscription( LocalDate.now() );
        IdParticipation id = new IdParticipation( this.getIdContrat(), this.getIdFormation() );
        p.setId( id );
        return p;
    }
}
