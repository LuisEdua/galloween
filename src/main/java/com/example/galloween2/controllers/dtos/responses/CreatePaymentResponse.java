package com.example.galloween2.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreatePaymentResponse {
    private Long id;

    private String ownerName;

    private Long cardNumber;

    private Long cvv;

    private String expirationDate;
}