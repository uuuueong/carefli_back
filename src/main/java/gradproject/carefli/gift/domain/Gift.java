package gradproject.carefli.gift.domain;

import gradproject.carefli.recommendedGift.domain.RecommendedGift;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Gift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "giftId", updatable = false)
    private Long giftId;

    @Column(nullable = false)
    private String category;

    @Column
    private String subCategory;

    @Column(nullable = false)
    private String giftName;

    @Column(nullable = false)
    private int price;

    @Column
    private String occasionType;

    @Column(nullable = false, length = 1024)
    private String giftUrl;

    @Column(length = 1024)
    private String giftImageUrl;

    @OneToMany(mappedBy = "gift", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<RecommendedGift> recommendedGifts = new ArrayList<>();

    @Builder
    public Gift(Long giftId, String category, String subCategory, String giftName, int price, String occasionType, String giftUrl, String giftImageUrl) {
        this.giftId = giftId;
        this.category = category;
        this.subCategory = subCategory;
        this.giftName = giftName;
        this.price = price;
        this.occasionType = occasionType;
        this.giftUrl = giftUrl;
        this.giftImageUrl = giftImageUrl;
    }
}
