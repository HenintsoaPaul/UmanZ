package mg.itu.rh.dto.talent;

import lombok.Data;
import mg.itu.rh.entity.talent.Talent;

@Data
public class AuthResponseDTO {
    private Long idTalent;
    private Long idContrat;
    private String email;
    private boolean isAdmin;

    public AuthResponseDTO( Talent talent ) {
        this.idTalent = talent.getIdTalent();
        this.email = talent.getMail();
        this.isAdmin = talent.isAdmin();
    }
}
