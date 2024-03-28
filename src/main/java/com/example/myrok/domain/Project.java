package com.example.myrok.domain;

import jakarta.persistence.*;
import jdk.jfr.Description;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_project")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    private Long id;

    @NonNull
    @Column(name = "project_name")
    private String projectName;

    @NonNull
    @Column(name = "team_name")
    private String teamName;

    @Description("삭제된 프로젝트, true의 경우 삭제된 프로젝트")
    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean deleted = false;

    @Description("해당 프로젝트에 참여하는 멤버리스트")
    @OneToMany(mappedBy = "project")
    private List<Member> memberList;

    @Column(name = "start_date")
    @Description("프로젝트 시작 일자")
    private LocalDate startDate;

    @Column(name = "end_date")
    @Description("프로젝트 마감 일자")
    private LocalDate endDate;

    @Column(name = "limit_member")
    @Description("참가자 제한 6명이 기본 참여 멤버")
    @Builder.Default
    private int limitMember = 6;

    public void changeDeleted(){this.deleted = true;}
}
