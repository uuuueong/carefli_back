package gradproject.carefli.giftRecommendationSet.repository;

import gradproject.carefli.giftRecommendationSet.domain.GiftRecommendationSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GiftRecommendationSetRepository extends JpaRepository<GiftRecommendationSet, Long> {
    List<GiftRecommendationSet> findByUser_UserIdAndConnection_ConnectionId(Long userId, Long connectionId);
}
