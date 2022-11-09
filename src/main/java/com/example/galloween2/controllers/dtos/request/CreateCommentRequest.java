package com.example.galloween2.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateCommentRequest {
    private String comment;
    private String date;
}
