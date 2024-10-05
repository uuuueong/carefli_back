package gradproject.carefli.recommendedGift.repository;

import gradproject.carefli.recommendedGift.domain.RecommendedGift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendedGiftRepository extends JpaRepository<RecommendedGift, Long> {
    List<RecommendedGift> findAllByGiftRecommendationSet_RecommendationSetId(Long recommendationSetId);
}
