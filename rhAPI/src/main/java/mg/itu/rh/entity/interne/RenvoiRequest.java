package mg.itu.rh.entity.interne;

import lombok.Data;

@Data
public class RenvoiRequest {
    private String email;
    private String motif;
    private String date;
}

