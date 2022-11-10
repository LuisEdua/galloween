package com.example.galloween2.controllers;

import com.example.galloween2.controllers.dtos.request.CreatePaymentRequest;
import com.example.galloween2.controllers.dtos.responses.CreatePaymentResponse;
import com.example.galloween2.services.interfaces.IPaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("payment")
public class PaymentController{
@Autowired
private IPaymentService service;


    @PostMapping("{user_id}")
    public CreatePaymentResponse create(@RequestBody CreatePaymentRequest request, @PathVariable Long user_id) {
        return service.create(request, user_id);
    }

    @GetMapping("{id}")
    public List<CreatePaymentResponse> listPaymentByUser(@PathVariable Long id) {
        return service.list(id);
    }

    @PutMapping("{id}")
    public CreatePaymentResponse update(@PathVariable Long id, @RequestBody CreatePaymentRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}


