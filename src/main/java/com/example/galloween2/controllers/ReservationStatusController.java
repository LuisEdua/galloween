package com.example.galloween2.controllers;

import com.example.galloween2.controllers.dtos.request.CreateReservationStatusRequest;
import com.example.galloween2.controllers.dtos.responses.CreateReservationStatusResponse;
import com.example.galloween2.entities.Reservation;
import com.example.galloween2.services.interfaces.IReservationStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reservation-status")
public class ReservationStatusController {
    @Autowired
    private IReservationStatusService service;

    @GetMapping("{idReservation}")
    public CreateReservationStatusResponse get(@PathVariable Long idReservation) {
        return service.get(idReservation);
    }

    @PutMapping("airplane/{id}")
    public void updateAirplane (@PathVariable Long id){
        service.updateAirplane(id);
    }

    @PutMapping("cruice-ship/{id}")
    public void updateCruiceShip (@PathVariable Long id){
        service.updateCruiceShip(id);
    }

    @PutMapping("bus/{id}")
    public void updateBus(@PathVariable Long id){
        service.updateBus(id);
    }

    @PutMapping("{id}")
    public CreateReservationStatusResponse update(@PathVariable Long id, @RequestBody CreateReservationStatusRequest request) {
        return service.update(id, request);
    }
}

