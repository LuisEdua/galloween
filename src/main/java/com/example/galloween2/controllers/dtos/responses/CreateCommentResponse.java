package com.example.galloween2.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateCommentResponse {
    private Long id;
    private String comment;
    private String date;
    private Long userId;
}
