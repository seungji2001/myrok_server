package com.example.myrok.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_tag")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private Long id;

    @Column(name = "tag_name")
    private String tagName;

    private int count;

    @ManyToOne
    @JoinColumn(name = "record_id")
    private Record record;

    public Tag(String tagName, int count) {
        this.tagName = tagName;
        this.count=count;
    }

    public void incrementCount() {
        this.count += 1;
    }

}
