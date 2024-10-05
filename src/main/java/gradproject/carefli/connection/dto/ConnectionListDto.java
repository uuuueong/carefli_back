package gradproject.carefli.connection.dto;

import gradproject.carefli.connection.domain.Connection;
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

    public ConnectionListDto(Long connectionId, String connectionName, String connectionImageUrl, String relationship) {
        this.connectionId = connectionId;
        this.connectionName = connectionName;
        this.connectionImageUrl = connectionImageUrl;
        this.relationship = relationship;
    }

    public static ConnectionListDto from(Connection connection) {
        return new ConnectionListDto(
                connection.getConnectionId(),
                connection.getName(),
                connection.getConnectionImageUrl(),
                connection.getRelationship()
        );
    }

}
