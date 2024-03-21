package com.example.myrok.domain;

import jakarta.persistence.*;
import jdk.jfr.Description;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Entity
@Table(name = "tb_project")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project {

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

    @Description("해당 프로젝트에 참여하는 멤버리스트")
    @OneToMany(mappedBy = "project")
    private List<Member> memberList;
}
