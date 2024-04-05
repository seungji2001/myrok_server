package com.example.myrok.domain;

import com.example.myrok.type.MemberProjectType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_member_project")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mp_id")
    private Long id;


    @OneToOne
    @Column(name = "m_id")
    private Member member;
    @OneToOne
    @Column(name = "m_id")
    private Project project;
    @Column(name = "member_project_type")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private MemberProjectType memberProjectType = MemberProjectType.NON_PROJECT_MEMBER;
    public void changeProject(Project project){
        this.project = project;
    }
    public void changeMemberProjectType(MemberProjectType memberProjectType){
        this.memberProjectType = memberProjectType;
    }
}
