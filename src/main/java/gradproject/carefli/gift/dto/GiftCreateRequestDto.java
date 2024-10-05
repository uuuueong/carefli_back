package gradproject.carefli.gift.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GiftCreateRequestDto {
    private String category;
    private String subCategory;
    private String giftName;
    private int price;
    private String giftUrl;
    private String giftImageUrl;

    @Builder
    public GiftCreateRequestDto(String category, String subCategory, String giftName, int price, String giftUrl, String giftImageUrl) {
        this.category = category;
        this.subCategory = subCategory;
        this.giftName = giftName;
        this.price = price;
        this.giftUrl = giftUrl;
        this.giftImageUrl = giftImageUrl;
    }
}
