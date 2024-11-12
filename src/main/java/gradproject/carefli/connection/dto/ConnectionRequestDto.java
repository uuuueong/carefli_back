package gradproject.carefli.connection.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import gradproject.carefli.connection.domain.Connection;
import gradproject.carefli.user.domain.MBTI;
import gradproject.carefli.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@NoArgsConstructor
public class ConnectionRequestDto {
    private String connectionName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthday;
    private String interestTag;
    private MBTI mbti;
    private String relationship;

    @Builder
    public ConnectionRequestDto(String connectionName, Date birthday, String interestTag, MBTI mbti, String relationship) {
        this.connectionName = connectionName;
        this.birthday = birthday;
        this.interestTag = interestTag;
        this.mbti = mbti;
        this.relationship = relationship;
    }

    public Connection toEntity(User user, String connectionImageUrl) {
        return Connection.builder()
                .user(user)
                .name(connectionName)
                .birthday(birthday)
                .interestTag(interestTag)
                .mbti(mbti)
                .relationship(relationship)
                .connectionImageUrl(connectionImageUrl)
                .build();
    }

}
