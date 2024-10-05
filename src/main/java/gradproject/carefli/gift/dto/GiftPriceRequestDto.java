package gradproject.carefli.gift.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GiftPriceRequestDto {
    private int minPrice;
    private int maxPrice;

    @Builder
    public GiftPriceRequestDto(int minPrice, int maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }
}
