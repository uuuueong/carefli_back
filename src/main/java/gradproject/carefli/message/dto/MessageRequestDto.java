package gradproject.carefli.message.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MessageRequestDto {
    private Long userId;
    private Long connectionId;
    private String occasionType;
    private String tone;
    private int length;
    private String text;

    @Builder
    public MessageRequestDto(Long userId, Long connectionId, String occasionType, String tone, int length, String text) {
        this.userId = userId;
        this.connectionId = connectionId;
        this.occasionType = occasionType;
        this.tone = tone;
        this.length = length;
        this.text = text;
    }

}
