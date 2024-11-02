package gradproject.carefli.connection.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import gradproject.carefli.user.domain.MBTI;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@NoArgsConstructor
public class ConnectionUpdateRequestDto {
    private Long connectionId;
    private String connectionName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthday;
    private String interestTag;
    private MBTI mbti;
    private String relationship;
    private String connectionImageUrl;

    @Builder
    public ConnectionUpdateRequestDto(Long connectionId, String connectionName, Date birthday, String interestTag, MBTI mbti,
                                      String relationship, String connectionImageUrl) {
        this.connectionId = connectionId;
        this.connectionName = connectionName;
        this.birthday = birthday;
        this.interestTag = interestTag;
        this.mbti = mbti;
        this.relationship = relationship;
        this.connectionImageUrl = connectionImageUrl;
    }

}
