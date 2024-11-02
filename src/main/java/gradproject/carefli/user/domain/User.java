package gradproject.carefli.user.domain;

import gradproject.carefli.connection.domain.Connection;
import gradproject.carefli.giftRecommendationSet.domain.GiftRecommendationSet;
import gradproject.carefli.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", updatable = false)
    private Long userId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GiftRecommendationSet> giftRecommendationSets = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Connection> connectionList = new ArrayList<>();

    @Column(nullable = false, unique = true, updatable = false)
    private String email;

    @Column(nullable = false, length = 16, unique = true)
    private String nickname;

    @Temporal(TemporalType.DATE)
    @Column
    private Date birthday;

    @Column(length = 512)
    private String userImageUrl;

    @Column(nullable = false)
    private String kakaoAccessToken;

    @Column
    private String interestTag;

    @Enumerated(EnumType.STRING)
    private MBTI mbti;

    @Builder
    public User(String nickname, Date birthday, String email, String userImageUrl, String kakaoAccessToken, String interestTag, MBTI mbti) {
        this.nickname = nickname;
        this.birthday = birthday;
        this.email = email;
        this.userImageUrl = userImageUrl;
        this.kakaoAccessToken = kakaoAccessToken;
        this.interestTag = interestTag;
        this.mbti = mbti;
    }

    // 액세스 토큰 업데이트
    public void updateKakaoAccessToken(String kakaoAccessToken) {
        this.kakaoAccessToken = kakaoAccessToken;
    }

    // 회원 정보 수정
    public void updateUser(String nickname, Date birthday, String userImageUrl, String interestTag, MBTI mbti){
        this.nickname = nickname;
        this.birthday = birthday;
        this.userImageUrl = userImageUrl;
        this.interestTag = interestTag;
        this.mbti = mbti;
    }
}
