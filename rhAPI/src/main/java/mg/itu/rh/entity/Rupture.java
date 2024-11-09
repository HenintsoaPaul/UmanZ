package mg.itu.rh.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Rupture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rupture")
    private Long idRupture;

    @Column(name = "date_rupture")
    private LocalDate dateRupture;

    private String motif;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contrat")
    private Contrat contrat;
}
