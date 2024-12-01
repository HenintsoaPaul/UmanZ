package mg.itu.rh.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.rh.other.POV;
import mg.itu.rh.entity.id.*;

@Entity
@Data
@Table(name = "categories_position")
public class CategoryPosition {

    @EmbeddedId
    private IdCategoryPosition id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonView({POV.Public.class})
    @JoinColumn(name = "id_poste",insertable = false,updatable = false)
    private Poste position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonView({POV.Full.class})
    @JoinColumn(name = "id_categories",insertable = false,updatable = false)
    private Category category;

    @Column(name = "point_categorie")
    private Integer pointCategorie;

}