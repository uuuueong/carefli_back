package gradproject.carefli.gift.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GiftRecommendationRequestDto {
    private List<Long> giftIds;
    private Long userId;
    private Long connectionId;
    private String occasionType;

    @Builder
    public GiftRecommendationRequestDto(List<Long> giftIds, Long userId, Long connectionId, String occasionType) {
        this.giftIds = giftIds;
        this.userId = userId;
        this.connectionId = connectionId;
        this.occasionType = occasionType;
    }
}
