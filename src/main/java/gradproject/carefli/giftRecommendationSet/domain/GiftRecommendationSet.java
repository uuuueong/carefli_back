package gradproject.carefli.giftRecommendationSet.domain;

import gradproject.carefli.connection.domain.Connection;
import gradproject.carefli.global.entity.BaseTimeEntity;
import gradproject.carefli.recommendedGift.domain.RecommendedGift;
import gradproject.carefli.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class GiftRecommendationSet extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recommendationSetId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "connectionId", nullable = false)
    private Connection connection;

    @OneToMany(mappedBy = "giftRecommendationSet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecommendedGift> recommendedGifts = new ArrayList<>();

    @Column
    private String occasionType;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Builder
    public GiftRecommendationSet(User user, Connection connection, String occasionType, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.user = user;
        this.connection = connection;
        this.occasionType = occasionType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
