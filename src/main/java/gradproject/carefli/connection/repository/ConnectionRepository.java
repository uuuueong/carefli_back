package gradproject.carefli.connection.repository;

import gradproject.carefli.connection.domain.Connection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ConnectionRepository extends JpaRepository<Connection, Long> {
    @Query("SELECT f from Connection f where f.connectionId = :connectionId")
    Optional<Connection> findByConnectionId(Long connectionId);
    @Query("SELECT f from Connection f where f.user.userId = :userId")
    List<Connection> findByUserId(Long userId);
    @Query("SELECT f from Connection f where f.user.userId = :userId and f.connectionId = :connectionId")
    Optional<Connection> findByUserIdAndConnectionId(Long userId, Long connectionId);
    @Query("SELECT c FROM Connection c WHERE c.name LIKE CONCAT('%', :searchWord, '%') AND c.user.userId = :userId")
    List<Connection> findByWordAndUserId(@Param("searchWord") String searchWord, @Param("userId") Long userId);
}
