package gradproject.carefli.message.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MessageResponseDto {
    private Long messageId;
    private Long userId;
    private Long connectionId;
    private String occasionType;
    private String tone;
    private int length;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public MessageResponseDto(Long messageId, Long userId, Long connectionId, String occasionType, String tone, int length, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.messageId = messageId;
        this.userId = userId;
        this.connectionId = connectionId;
        this.occasionType = occasionType;
        this.tone = tone;
        this.length = length;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
