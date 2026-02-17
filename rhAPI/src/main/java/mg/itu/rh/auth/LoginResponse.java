package mg.itu.rh.auth;

import lombok.Data;
import mg.itu.rh.entity.talent.Talent;

@Data
public class LoginResponse {
    private Long idTalent;
    private Long idContrat;
    private String email;
    private boolean isAdmin;
    private boolean mfaRequired;

    public LoginResponse(Talent talent) {
        this.idTalent = talent.getIdTalent();
        this.email = talent.getMail();
        this.isAdmin = talent.isAdmin();
    }
}
