package gradproject.carefli.gift.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GiftRecommendationResponseDto {
    private Long recommendationSetId;
    private Long userId;
    private Long connectionId;
    private String occasionType;
    private List<GiftResponseDto> recommendedGifts;

    @Builder
    public GiftRecommendationResponseDto(Long recommendationSetId, Long userId, Long connectionId, String occasionType, List<GiftResponseDto> recommendedGifts) {
        this.recommendationSetId = recommendationSetId;
        this.userId = userId;
        this.connectionId = connectionId;
        this.occasionType = occasionType;
        this.recommendedGifts = recommendedGifts;
    }
}
