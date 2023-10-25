package com.example.core06jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor  // request
@NoArgsConstructor   // 필드가 1개인 경우
@Getter              // swagger
@ToString
public class UpdateProductRequest {
    private int price;
}
