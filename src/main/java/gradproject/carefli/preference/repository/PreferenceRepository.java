package gradproject.carefli.preference.repository;

import gradproject.carefli.connection.domain.Connection;
import gradproject.carefli.gift.domain.Gift;
import gradproject.carefli.preference.domain.Preference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {
    Optional<Preference> findByGiftAndConnection(Gift gift, Connection connection);

    List<Preference> findAllByConnectionOrderByCreatedAtDesc(Connection connection);
}
