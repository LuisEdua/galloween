package com.example.galloween2.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class CreateDestinationRequest {
    private String city;
    private String state;
    private String country;
    private MultipartFile picture;
}
