package gradproject.carefli.connection.domain;

import gradproject.carefli.global.entity.BaseTimeEntity;
import gradproject.carefli.user.domain.InterestTag;
import gradproject.carefli.user.domain.MBTI;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Connection extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "connection_id", updatable = false)
    private Long id;

    @Column(name = "connection_name", nullable = false, length = 20)
    private String name;

    @Column
    private String email;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column
    private String phoneNumber;

    @Column
    private String connectionImageUrl;

    @Enumerated(EnumType.STRING)
    private InterestTag interestTag;

    @Enumerated(EnumType.STRING)
    private MBTI mbti;

    @Enumerated(EnumType.STRING)
    private Relationship relationship;

    @Column(length = 50)
    private String companyName;

    @Column(length = 50)
    private String position;

    @Builder
    public Connection(String name, String email, Date birthday, String phoneNumber, String connectionImageUrl, InterestTag interestTag, MBTI mbti, Relationship relationship, String companyName, String position) {
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.connectionImageUrl = connectionImageUrl;
        this.interestTag = interestTag;
        this.mbti = mbti;
        this.relationship = relationship;
        this.companyName = companyName;
        this.position = position;
    }

}
