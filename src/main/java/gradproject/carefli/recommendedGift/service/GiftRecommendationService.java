package gradproject.carefli.recommendedGift.service;

import gradproject.carefli.connection.domain.Connection;
import gradproject.carefli.connection.repository.ConnectionRepository;
import gradproject.carefli.gift.domain.Gift;
import gradproject.carefli.gift.dto.GiftRecommendationRequestDto;
import gradproject.carefli.gift.dto.GiftRecommendationResponseDto;
import gradproject.carefli.gift.dto.GiftResponseDto;
import gradproject.carefli.gift.repository.GiftRepository;
import gradproject.carefli.giftRecommendationSet.domain.GiftRecommendationSet;
import gradproject.carefli.giftRecommendationSet.repository.GiftRecommendationSetRepository;
import gradproject.carefli.recommendedGift.domain.RecommendedGift;
import gradproject.carefli.recommendedGift.repository.RecommendedGiftRepository;
import gradproject.carefli.user.domain.User;
import gradproject.carefli.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GiftRecommendationService {

    private final GiftRecommendationSetRepository giftRecommendationSetRepository;
    private final RecommendedGiftRepository recommendedGiftRepository;
    private final UserRepository userRepository;
    private final ConnectionRepository connectionRepository;
    private final GiftRepository giftRepository;  // GiftRepository 추가

    @Transactional
    public GiftRecommendationResponseDto saveRecommendationSet(GiftRecommendationRequestDto requestDto) {
        User user = getUserById(requestDto.getUserId());
        Connection connection = getConnectionById(requestDto.getConnectionId());

        GiftRecommendationSet recommendationSet = createAndSaveGiftRecommendationSet(user, connection, requestDto.getOccasionType());
        List<RecommendedGift> recommendedGifts = saveRecommendedGifts(requestDto.getGiftIds(), recommendationSet);
        return buildRecommendationResponseDto(recommendationSet, recommendedGifts);
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + userId));
    }

    private Connection getConnectionById(Long connectionId) {
        return connectionRepository.findById(connectionId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid connection ID: " + connectionId));
    }

    private GiftRecommendationSet createAndSaveGiftRecommendationSet(User user, Connection connection, String occasionType) {
        GiftRecommendationSet recommendationSet = GiftRecommendationSet.builder()
                .user(user)
                .connection(connection)
                .occasionType(occasionType)
                .build();

        return giftRecommendationSetRepository.save(recommendationSet);
    }

    // 추천된 선물 리스트를 저장
    private List<RecommendedGift> saveRecommendedGifts(List<Long> giftIds, GiftRecommendationSet recommendationSet) {
        // Gift ID를 기반으로 Gift 객체를 조회하여 RecommendedGift 생성
        List<RecommendedGift> recommendedGifts = giftIds.stream()
                .map(giftId -> {
                    Gift gift = giftRepository.findById(giftId)
                            .orElseThrow(() -> new IllegalArgumentException("Invalid gift ID: " + giftId));

                    return RecommendedGift.builder()
                            .giftRecommendationSet(recommendationSet)
                            .gift(gift)  // Gift 객체 전체를 설정
                            .giftRank(0) // 초기 rank 값 일단 0으로 설정
                            .build();
                })
                .collect(Collectors.toList());

        return recommendedGiftRepository.saveAll(recommendedGifts);
    }

    // GiftRecommendationResponseDto를 생성
    private GiftRecommendationResponseDto buildRecommendationResponseDto(GiftRecommendationSet recommendationSet, List<RecommendedGift> recommendedGifts) {
        List<GiftResponseDto> recommendedGiftDtos = recommendedGifts.stream()
                .map(this::convertToGiftResponseDto)
                .collect(Collectors.toList());

        return GiftRecommendationResponseDto.builder()
                .recommendationSetId(recommendationSet.getRecommendationSetId())
                .userId(recommendationSet.getUser().getUserId())
                .connectionId(recommendationSet.getConnection().getConnectionId())
                .occasionType(recommendationSet.getOccasionType())
                .recommendedGifts(recommendedGiftDtos)
                .build();
    }

    // RecommendedGift 엔티티를 GiftResponseDto로 변환
    private GiftResponseDto convertToGiftResponseDto(RecommendedGift recommendedGift) {
        Gift gift = recommendedGift.getGift();  // RecommendedGift에서 Gift 정보 추출

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

    @Transactional(readOnly = true)
    public GiftRecommendationResponseDto getRecommendationSet(Long recommendationSetId) {
        GiftRecommendationSet recommendationSet = giftRecommendationSetRepository.findById(recommendationSetId)
                .orElseThrow(() -> new IllegalArgumentException("Recommendation set not found for id: " + recommendationSetId));

        // 추천 선물 리스트 조회 및 GiftResponseDto로 변환
        List<GiftResponseDto> recommendedGifts = recommendationSet.getRecommendedGifts().stream()
                .map(this::convertToGiftResponseDto)
                .collect(Collectors.toList());

        return GiftRecommendationResponseDto.builder()
                .recommendationSetId(recommendationSet.getRecommendationSetId())
                .userId(recommendationSet.getUser().getUserId())
                .connectionId(recommendationSet.getConnection().getConnectionId())
                .occasionType(recommendationSet.getOccasionType())
                .recommendedGifts(recommendedGifts)
                .build();
    }
}


