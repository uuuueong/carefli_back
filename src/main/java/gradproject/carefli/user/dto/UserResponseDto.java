package gradproject.carefli.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import gradproject.carefli.global.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import gradproject.carefli.user.domain.InterestTag;
import gradproject.carefli.user.domain.MBTI;
import gradproject.carefli.user.domain.User;
@Getter
@AllArgsConstructor
public class UserResponseDto extends BaseTimeEntity {
    private Long userId;
    private String nickname;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthday;
    private String userImageUrl;
    private InterestTag interestTag;
    private MBTI mbti;


    public static UserResponseDto from (User user) {
        return new UserResponseDto(user.getUserId(),
                user.getNickname(),
                user.getEmail(),
                user.getBirthday(),
                user.getUserImageUrl(),
                user.getInterestTag(),
                user.getMbti());
    }
}
