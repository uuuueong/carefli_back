package gradproject.carefli.message.repository;

import gradproject.carefli.message.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM Message m WHERE m.user.userId = :userId")
    List<Message> findByUserId(Long userId);

    @Query("SELECT m FROM Message m WHERE m.connection IS NULL OR m.connection.connectionId = :connectionId")
    List<Message> findByConnectionId(@Param("connectionId") Long connectionId);

    @Query("SELECT m FROM Message m WHERE m.user.userId = :userId AND (m.connection IS NULL OR m.connection.connectionId = :connectionId)")
    List<Message> findByUserIdAndConnectionId(@Param("userId") Long userId, @Param("connectionId") Long connectionId);
}
