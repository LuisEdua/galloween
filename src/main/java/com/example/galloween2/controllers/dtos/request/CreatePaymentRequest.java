package com.example.galloween2.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreatePaymentRequest {
    private String ownerName;

    private Long cardNumber;

    private Long cvv;

    private String expirationDate;
}
