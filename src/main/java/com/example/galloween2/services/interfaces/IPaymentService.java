package com.example.galloween2.services.interfaces;

import com.example.galloween2.controllers.dtos.request.CreatePaymentRequest;
import com.example.galloween2.controllers.dtos.responses.CreatePaymentResponse;
import com.example.galloween2.entities.Payment;

import java.util.List;

public interface IPaymentService {
    CreatePaymentResponse create(CreatePaymentRequest request, Long user_id);

    List<CreatePaymentResponse> list(Long id);

    void delete(Long id);

    void findByUser(Long id);

    Payment findById(Long id);

}
