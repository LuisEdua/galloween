package com.example.galloween2.controllers;

import com.example.galloween2.controllers.dtos.request.CreateReservationStatusRequest;
import com.example.galloween2.controllers.dtos.responses.CreateReservationStatusResponse;
import com.example.galloween2.services.interfaces.IReservationStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reservation-status")
public class ReservationStatusController {
    @Autowired
    private IReservationStatusService service;

    @GetMapping("{id}")
    public CreateReservationStatusResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    /**@GetMapping
    public List<CreateReservationStatusResponse> list() {
        return service.list();
    }**/

    @PutMapping("{id}")
    public CreateReservationStatusResponse update(@PathVariable Long id, @RequestBody CreateReservationStatusRequest request) {
        return service.update(id, request);
    }
}

