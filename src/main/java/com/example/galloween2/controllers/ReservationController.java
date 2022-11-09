package com.example.galloween2.controllers;

import com.example.galloween2.controllers.dtos.request.CreateReservationRequest;
import com.example.galloween2.controllers.dtos.responses.CreateReservationResponse;
import com.example.galloween2.services.interfaces.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reservation")
public class ReservationController {
    @Autowired
    private IReservationService service;


    @PostMapping("{user_id}")
    public CreateReservationResponse create(@RequestBody CreateReservationRequest request, @PathVariable Long user_id) {
        return service.create(request, user_id);
    }

    @GetMapping("{userId}")
    public List<CreateReservationResponse> list(@PathVariable Long userId) {
        return service.list(userId);
    }

    @PutMapping("{id}")
    public CreateReservationResponse update(@PathVariable Long id, @RequestBody CreateReservationRequest request) {
        return service.update(id, request);
    }
}

