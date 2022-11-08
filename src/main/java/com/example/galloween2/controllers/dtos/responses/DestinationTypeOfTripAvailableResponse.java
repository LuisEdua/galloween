package com.example.galloween2.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DestinationTypeOfTripAvailableResponse {
    private Long id;
    private String city;
    private String state;
    private String country;
    private String photography;
}
