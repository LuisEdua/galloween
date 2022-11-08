package com.example.galloween2.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateUserRequest {
    private String fullName;
    private String email;
    private String password;
    private Integer age;
    private Long cellphone;
}
