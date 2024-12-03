package mg.itu.rh.dto.rupture;

import lombok.Data;

@Data
public abstract class RuptureRequest {
    protected Long idContrat;
    protected String motif;
    protected String date;

    abstract public String getSubject();
}
