package gradproject.carefli.recommendedGift.repository;

import gradproject.carefli.recommendedGift.domain.RecommendedGift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendedGiftRepository extends JpaRepository<RecommendedGift, Long> {
}
