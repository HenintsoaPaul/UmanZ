package mg.itu.rh.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('chat_id_chat_seq')")
    @Column(name = "id_chat", nullable = false)
    private Long id;

    @Column(name = "mot_cle", nullable = false, length = 50)
    private String motCle;

    @Column(name = "reponse", nullable = false, length = Integer.MAX_VALUE)
    private String reponse;

}