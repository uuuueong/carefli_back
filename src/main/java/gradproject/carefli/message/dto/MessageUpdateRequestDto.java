package gradproject.carefli.message.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MessageUpdateRequestDto {
    private String content;

    @Builder
    public MessageUpdateRequestDto(String content) {
        this.content = content;
    }
}
