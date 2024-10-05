package gradproject.carefli.connection.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import gradproject.carefli.connection.domain.*;
import gradproject.carefli.global.entity.BaseTimeEntity;
import gradproject.carefli.user.domain.InterestTag;
import gradproject.carefli.user.domain.MBTI;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ConnectionResponseDto extends BaseTimeEntity {
    private Long connectionId;
    private Long userId;
    private String connectionName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthday;
    private String connectionImageUrl;
    private InterestTag interestTag;
    private MBTI mbti;
    private String relationship;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ConnectionResponseDto(Long connectionId, Long userId, String connectionName,
                                 Date birthday, String connectionImageUrl, InterestTag interestTag,
                                 MBTI mbti, String relationship, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.connectionId = connectionId;
        this.userId = userId;
        this.connectionName = connectionName;
        this.birthday = birthday;
        this.connectionImageUrl = connectionImageUrl;
        this.interestTag = interestTag;
        this.mbti = mbti;
        this.relationship = relationship;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static ConnectionResponseDto from(Connection connection) {
        return new ConnectionResponseDto(
                connection.getConnectionId(),
                connection.getUser().getUserId(),
                connection.getName(),
                connection.getBirthday(),
                connection.getConnectionImageUrl(),
                connection.getInterestTag(),
                connection.getMbti(),
                connection.getRelationship(),
                connection.getCreatedAt(),
                connection.getUpdatedAt()
        );
    }
}
