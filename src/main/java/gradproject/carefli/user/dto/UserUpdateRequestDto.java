package gradproject.carefli.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import gradproject.carefli.user.domain.MBTI;
import java.util.Date;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String nickname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthday;
    private String userImageUrl;
    private String  interestTag;
    private MBTI mbti;

    @Builder
    public UserUpdateRequestDto(String nickname, Date birthday, String userImageUrl, String interestTag, MBTI mbti) {
        this.nickname = nickname;
        this.birthday = birthday;
        this.userImageUrl = userImageUrl; // 기본 이미지 URL
        this.interestTag = interestTag;
        this.mbti = mbti;
    }
}
