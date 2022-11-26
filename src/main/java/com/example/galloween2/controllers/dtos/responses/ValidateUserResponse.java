package com.example.galloween2.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ValidateUserResponse {
    private Long id;

    private String email;

    private String Password;

    private String fullName;

    private Integer age;

    private Long cellphone;

    private String role;
}
