package com.example.myrok.domain;

import jakarta.persistence.*;
import jdk.jfr.Description;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_record")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_id")
    private Long id;

    @NonNull
    @Column(name = "record_name")
    private String recordName;

    @NonNull
    @Column(name = "record_date")
    private LocalDate recordDate;

    @NonNull
    @Column(name = "record_content")
    private String recordContent;

    @Description("해당 프로젝트에 참여하는 멤버리스트")
    @OneToMany(mappedBy = "record")
    private List<Tag> tagList;

}
