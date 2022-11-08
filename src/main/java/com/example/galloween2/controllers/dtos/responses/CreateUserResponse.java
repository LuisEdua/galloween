package com.example.galloween2.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserResponse {
    private Long id;
    private String fullName;
    private String email;
    private String password;
    private Integer age;
    private Long cellphone;
}
