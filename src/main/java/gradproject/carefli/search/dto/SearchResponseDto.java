package gradproject.carefli.search.dto;

import gradproject.carefli.connection.domain.Connection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchResponseDto {
    private Long connectionId;
    private String connectionName;
    private String connectionImageUrl;
    private String relationship;

    public SearchResponseDto(Long connectionId, String connectionName, String connectionImageUrl, String relationship) {
        this.connectionId = connectionId;
        this.connectionName = connectionName;
        this.connectionImageUrl = connectionImageUrl;
        this.relationship = relationship;
    }

    public static SearchResponseDto from(Connection connection) {
        return new SearchResponseDto(
                connection.getConnectionId(),
                connection.getName(),
                connection.getConnectionImageUrl(),
                connection.getRelationship()
        );
    }

}

