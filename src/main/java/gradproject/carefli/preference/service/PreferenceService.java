package gradproject.carefli.preference.service;

import gradproject.carefli.connection.domain.Connection;
import gradproject.carefli.connection.repository.ConnectionRepository;
import gradproject.carefli.gift.domain.Gift;
import gradproject.carefli.gift.dto.GiftResponseDto;
import gradproject.carefli.gift.repository.GiftRepository;
import gradproject.carefli.preference.domain.Preference;
import gradproject.carefli.preference.dto.CategoryMbtiResponseDto;
import gradproject.carefli.preference.repository.CategoryMbtiPreference;
import gradproject.carefli.preference.repository.PreferenceRepository;
import gradproject.carefli.user.domain.MBTI;
import gradproject.carefli.user.domain.User;
import gradproject.carefli.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PreferenceService {
    private final PreferenceRepository preferenceRepository;
    private final GiftRepository giftRepository;
    private final UserRepository userRepository;
    private final ConnectionRepository connectionRepository;

    @Transactional
    public void likeGift(Long userId, Long connectionId, Long giftId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Connection connection = connectionRepository.findById(connectionId)
                .orElseThrow(()-> new IllegalArgumentException("Connection not found"));
        MBTI connectionMbti = connection.getMbti();
        Gift gift = giftRepository.findById(giftId)
                .orElseThrow(() -> new IllegalArgumentException("Gift not found"));

        Preference preference = preferenceRepository.findByGiftAndConnection(gift, connection)
                .orElse(null);

        if (preference != null) {
            // 이미 좋아요가 된 상태면, 좋아요 취소 후 Preference 삭제
            preferenceRepository.delete(preference);
        } else {
            // 좋아요가 없는 상태면, Preference 객체를 생성하고 저장
            preference = Preference.builder()
                    .gift(gift)
                    .user(user)
                    .connection(connection)
                    .category(gift.getCategory())
                    .mbti(connectionMbti)
                    .preferenceCount(1)
                    .build();
            preferenceRepository.save(preference);
        }
    }

    //좋아요한 선물 히스토리 조회
    @Transactional(readOnly = true)
    public List<GiftResponseDto> getLikedGiftsByConnection(Long connectionId) {
        Connection connection = connectionRepository.findById(connectionId)
                .orElseThrow(() -> new IllegalArgumentException("connection not found"));

        List<Preference> preferences = preferenceRepository.findAllByConnectionOrderByCreatedAtDesc(connection);

        return preferences.stream()
                .map(preference -> {
                    Gift gift = preference.getGift();
                    return GiftResponseDto.builder()
                            .giftId(gift.getGiftId())
                            .category(gift.getCategory())
                            .subCategory(gift.getSubCategory())
                            .giftName(gift.getGiftName())
                            .price(gift.getPrice())
                            .giftUrl(gift.getGiftUrl())
                            .giftImageUrl(gift.getGiftImageUrl())
                            .createdAt(preference.getCreatedAt())
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CategoryMbtiResponseDto> getCategoryMbtiPreferences() {
        List<CategoryMbtiPreference> rawPreferences = preferenceRepository.findCategoryMbtiPreferences();

        Map<String, Map<String, Long>> groupedPreferences = new HashMap<>();
        for (CategoryMbtiPreference rawPreference : rawPreferences) {
            groupedPreferences
                    .computeIfAbsent(rawPreference.getCategory(), k -> new HashMap<>())
                    .put(rawPreference.getMbti(), rawPreference.getPreferenceCount());
        }

        return groupedPreferences.entrySet().stream()
                .map(entry -> new CategoryMbtiResponseDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
