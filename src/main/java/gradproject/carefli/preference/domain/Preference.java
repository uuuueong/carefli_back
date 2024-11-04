package gradproject.carefli.preference.domain;

import gradproject.carefli.connection.domain.Connection;
import gradproject.carefli.gift.domain.Gift;
import gradproject.carefli.user.domain.MBTI;
import gradproject.carefli.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Preference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preferenceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "giftId", nullable = false)
    private Gift gift;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false) // userId와 연관관계 설정
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "connectionId", nullable = false) // connectionId와 연관관계 설정
    private Connection connection;

    @Column
    private String category;

    @Column
    @Enumerated(EnumType.STRING)
    private MBTI mbti;

    @Column
    private int preferenceCount;

    @Builder
    public Preference(Gift gift, User user, Connection connection, String category, MBTI mbti, int preferenceCount) {
        this.gift = gift;
        this.user = user;
        this.connection = connection;
        this.category = category;
        this.mbti = mbti;
        this.preferenceCount = preferenceCount;
    }

    public void addPreferenceCount() {
        this.preferenceCount += 1;
    }

    public void subPreferenceCount() {
        this.preferenceCount -= 1;
    }

}
