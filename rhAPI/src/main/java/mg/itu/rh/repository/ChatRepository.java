package mg.itu.rh.repository;

import mg.itu.rh.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query(value = """
        SELECT * FROM chat
        WHERE LOWER(:message)
        LIKE '%' || LOWER(mot_cle) || '%'
        LIMIT 1
    """, nativeQuery = true)
    Chat findResponse(@Param("message") String message);

}
