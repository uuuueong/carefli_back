package gradproject.carefli.gift.controller;

import gradproject.carefli.gift.dto.GiftRecommendationRequestDto;
import gradproject.carefli.gift.dto.GiftRecommendationResponseDto;
import gradproject.carefli.gift.dto.GiftResponseDto;
import gradproject.carefli.gift.service.GiftService;
import gradproject.carefli.oauth.customAnnotation.AuthUser;
import gradproject.carefli.preference.dto.CategoryMbtiResponseDto;
import gradproject.carefli.preference.service.PreferenceService;
import gradproject.carefli.recommendedGift.service.GiftRecommendationService;
import gradproject.carefli.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gifts")
@RequiredArgsConstructor

public class GiftController {
    private final GiftService giftService;
    private final GiftRecommendationService giftRecommendationService;
    private final PreferenceService preferenceService;

    //가격대별 선물 리스트 조회
    @GetMapping("/list")
    public ResponseEntity<List<GiftResponseDto>> getAllGiftsByPrice(@RequestParam int minPrice, @RequestParam int maxPrice) {
        List<GiftResponseDto> gifts = giftService.findGiftsByPrice(minPrice, maxPrice);
        return ResponseEntity.ok(gifts);
    }

    //선물 상세 조회
    @GetMapping("/details")
    public ResponseEntity<List<GiftResponseDto>> getGiftDetailsByIds(@RequestParam List<Long> giftIds) {
        List<GiftResponseDto> giftDetails = giftService.findGiftDetailsByIds(giftIds);
        return ResponseEntity.ok(giftDetails);
    }

    //추천된 선물 리스트 저장
    @PostMapping("/recommended/save")
    public ResponseEntity<GiftRecommendationResponseDto> saveRecommendation(@RequestBody GiftRecommendationRequestDto requestDto) {
        GiftRecommendationResponseDto savedRecommendation = giftRecommendationService.saveRecommendationSet(requestDto);
        return ResponseEntity.ok(savedRecommendation);
    }

    //선물 추천 리스트 조회
    @GetMapping("/recommendations/{recommendationSetId}")
    public ResponseEntity<GiftRecommendationResponseDto> getRecommendationSet(@PathVariable Long recommendationSetId) {
        GiftRecommendationResponseDto recommendationResponse = giftRecommendationService.getRecommendationSet(recommendationSetId);
        return ResponseEntity.ok(recommendationResponse);

    }

    //선물 좋아요 기능
    @PostMapping("/like")
    public ResponseEntity<String> likeGift(@AuthUser User user, @RequestParam Long connectionId, @RequestParam Long giftId) {
        preferenceService.likeGift(user.getUserId(),connectionId, giftId);
        return ResponseEntity.ok("좋아요가 반영되었습니다.");
    }

    //좋아요한 선물 목록 조회
    @GetMapping("/like")
    public ResponseEntity<List<GiftResponseDto>> getLikeGift(@RequestParam Long connectionId) {
        List<GiftResponseDto> likedGifts = preferenceService.getLikedGiftsByConnection(connectionId);
        return ResponseEntity.ok(likedGifts);
    }

    //카테고리별 MBTI 선호도 조회
    @GetMapping("/preference")
    public ResponseEntity<List<CategoryMbtiResponseDto>> getCategoryMbtiPreferences() {
        List<CategoryMbtiResponseDto> response = preferenceService.getCategoryMbtiPreferences();
        return ResponseEntity.ok(response);
    }
}
