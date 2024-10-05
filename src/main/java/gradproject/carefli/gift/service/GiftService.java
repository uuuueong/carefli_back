package gradproject.carefli.gift.service;

import gradproject.carefli.gift.domain.Gift;
import gradproject.carefli.gift.dto.GiftResponseDto;
import gradproject.carefli.gift.repository.GiftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GiftService {
    private final GiftRepository giftRepository;

    @Transactional(readOnly = true)
    public List<GiftResponseDto> findGiftsByPrice(int minPrice, int maxPrice) {
        List<Gift> gifts = giftRepository.findAllByPriceBetween(minPrice, maxPrice);
        return gifts.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<GiftResponseDto> findGiftDetailsByIds(List<Long> giftIds) {
        List<Gift> gifts = giftRepository.findAllById(giftIds);
        return gifts.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private GiftResponseDto convertToDto(Gift gift) {
        return GiftResponseDto.builder()
                .giftId(gift.getGiftId())
                .category(gift.getCategory())
                .subCategory(gift.getSubCategory())
                .giftName(gift.getGiftName())
                .price(gift.getPrice())
                .giftUrl(gift.getGiftUrl())
                .giftImageUrl(gift.getGiftImageUrl())
                .build();
    }
}
