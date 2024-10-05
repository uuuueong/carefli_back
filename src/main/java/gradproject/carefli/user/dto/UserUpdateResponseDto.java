package gradproject.carefli.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import gradproject.carefli.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import gradproject.carefli.user.domain.InterestTag;
import gradproject.carefli.user.domain.MBTI;
import gradproject.carefli.user.domain.User;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserUpdateResponseDto extends BaseTimeEntity {
    private Long userId;
    private String nickname;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthday;
    private String userImageUrl;
    private InterestTag interestTag;
    private MBTI mbti;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static UserUpdateResponseDto from (User user) {
        return new UserUpdateResponseDto(user.getUserId(),
                user.getNickname(),
                user.getEmail(),
                user.getBirthday(),
                user.getUserImageUrl(),
                user.getInterestTag(),
                user.getMbti(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }
}