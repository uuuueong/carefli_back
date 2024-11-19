package gradproject.carefli.connection.dto;

import gradproject.carefli.connection.domain.Connection;
import gradproject.carefli.user.domain.MBTI;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ConnectionListDto {
    private Long connectionId;
    private String connectionName;
    private String connectionImageUrl;
    private String relationship;
    private MBTI mbti;

    public ConnectionListDto(Long connectionId, String connectionName, String connectionImageUrl, String relationship, MBTI mbti) {
        this.connectionId = connectionId;
        this.connectionName = connectionName;
        this.connectionImageUrl = connectionImageUrl;
        this.relationship = relationship;
        this.mbti = mbti;
    }

    public static ConnectionListDto from(Connection connection) {
        return new ConnectionListDto(
                connection.getConnectionId(),
                connection.getName(),
                connection.getConnectionImageUrl(),
                connection.getRelationship(),
                connection.getMbti()
        );
    }

}
