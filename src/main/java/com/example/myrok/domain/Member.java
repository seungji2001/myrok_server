package com.example.myrok.domain;

import com.example.myrok.type.MemberProjectType;
import com.example.myrok.type.Role;
import com.example.myrok.type.ELoginProvider;
import jakarta.persistence.*;
import jdk.jfr.Category;
import jdk.jfr.Description;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_member")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "m_id")
    private Long id;

    private String name;

    @Description("소셜로그인 인증 후 받는 로그인정보")
    @Column(name = "social_id")
    private String socialId;

    @Column(name = "provider")
    @Enumerated(EnumType.STRING)
    private ELoginProvider provider;

    @Description("랜덤 패스워드 부야")
    private String password;

    @Description("탈퇴한 회원, true의 경우 탈퇴한 회원")
    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean deleted = false;

    @Column(name = "img_url")
    @Description("이미지 url")
    private String imgUrl;

    @OneToMany(mappedBy = "member")
    private List<MemberProject> memberProjects;


    @Column(name = "is_login", columnDefinition = "TINYINT(1)", nullable = false)
    private Boolean isLogin;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Builder
    public Member(final String socialId,
                  final String password,
                  final ELoginProvider provider
    ) {
        this.socialId = socialId;
        this.password = password;
        this.provider = provider;
        this.isLogin = false;
        this.refreshToken = null;
        this.createdAt = LocalDateTime.now();
    }
}
