package gradproject.carefli.gift.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GiftResponseDto {
    private Long giftId;
    private String category;
    private String subCategory;
    private String giftName;
    private int price;
    private String giftUrl;
    private String giftImageUrl;

    @Builder
    public GiftResponseDto(Long giftId, String giftName, int price, String category, String subCategory, String giftUrl, String giftImageUrl) {
        this.giftId = giftId;
        this.giftName = giftName;
        this.price = price;
        this.category = category;
        this.subCategory = subCategory;
        this.giftUrl = giftUrl;
        this.giftImageUrl = giftImageUrl;
    }
}
