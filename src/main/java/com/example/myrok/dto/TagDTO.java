package com.example.myrok.dto;

import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class TagDTO{
    private String tagName;
    private Long percentage;
}