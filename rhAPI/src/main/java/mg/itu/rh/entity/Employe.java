package mg.itu.rh.entity;

import mg.itu.rh.entity.id.*;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee")
    private Long idEmploye;

    @Column(name = "nom")
    private String name;

    @Column(name = "email")
    private String email;

    @Embedded
    private IdCategoryPosition categoriesPositionId;

    @Column(name = "date_of_hire")
    private LocalDate dateOfHire;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "id_poste", referencedColumnName = "id_poste", insertable = false, updatable = false),
        @JoinColumn(name = "id_categories", referencedColumnName = "id_categories", insertable = false, updatable = false)
    })
    private CategoryPosition categoriesPosition;

}
