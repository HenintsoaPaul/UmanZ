package mg.itu.rh.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HeureSupplementaireRequest {
    private Long idContrat;
    private String motif;
    private LocalDateTime dateHeureDebut;
    private Double nbHeure;
}
