package com.example.core10validexception.dto;

import com.example.core10validexception.annotation.Telephone;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ValidationRequest {

    @NotBlank
    private String name;

    @Min(value = 20)
    @Max(value = 40)
    private int age;

    @Size(min = 1, max = 20)
    private String address;

    @Email
    private String email;

    @Telephone
    private String phoneNumber;
}
