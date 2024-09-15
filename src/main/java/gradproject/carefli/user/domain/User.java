package gradproject.carefli.user.domain;

import gradproject.carefli.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 16, unique = true)
    private String nickname;

    @Temporal(TemporalType.DATE)
    @Column
    private Date birthday;

    @Column(length = 512)
    private String userImageUrl;

    @Column
    private String kakaoAccessToken;

    @Enumerated(EnumType.STRING)
    private InterestTag interestTag;

    @Enumerated(EnumType.STRING)
    private MBTI mbti;

    @Builder
    public User(String nickname, Date birthday, String email, String userImageUrl, String kakaoAccessToken, InterestTag interestTag, MBTI mbti) {
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
    public void updateUser(String nickname, Date birthday, String userImageUrl, InterestTag interestTag, MBTI mbti){
        this.nickname = nickname;
        if (birthday != null) {
            this.birthday = birthday;
        }
        if (userImageUrl != null) {
            this.userImageUrl = userImageUrl;
        }
        if (interestTag != null) {
            this.interestTag = interestTag;
        }
        if (mbti != null) {
            this.mbti = mbti;
        }
    }
}
