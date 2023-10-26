package com.example.core12rest.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PostDto {
    private Long id;
    private String title;
    private String body;
    private Long userId;
}
