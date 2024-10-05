package gradproject.carefli.message.domain;

import gradproject.carefli.connection.domain.Connection;
import gradproject.carefli.global.entity.BaseTimeEntity;
import gradproject.carefli.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "connectionId")
    private Connection connection;

    @Column(nullable = false) //나중에 Enum type으로 바꾸기
    private String occasionType;

    @Column(nullable = false) //나중에 Enum type으로 바꾸기
    private String tone;

    @Column(nullable = false)
    private int length;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public Message(User user, Connection connection, String occasionType, String tone, int length, String text) {
        this.user = user;
        this.connection = connection;
        this.occasionType = occasionType;
        this.tone = tone;
        this.length = length;
        this.text = text;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void updateContent(String text){
        this.text = text;
        this.updatedAt = LocalDateTime.now();
    }

}
