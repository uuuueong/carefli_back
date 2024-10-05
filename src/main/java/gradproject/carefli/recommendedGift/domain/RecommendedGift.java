package gradproject.carefli.recommendedGift.domain;

import gradproject.carefli.gift.domain.Gift;
import gradproject.carefli.giftRecommendationSet.domain.GiftRecommendationSet;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecommendedGift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recommendedGiftId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recommendationSetId", nullable = false)
    private GiftRecommendationSet giftRecommendationSet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "giftId", nullable = false)
    private Gift gift;

    @Column
    private int giftRank;

    @Builder
    public RecommendedGift(GiftRecommendationSet giftRecommendationSet, Gift gift, int giftRank) {
        this.giftRecommendationSet = giftRecommendationSet;
        this.gift = gift;
        this.giftRank = giftRank;
    }
}
