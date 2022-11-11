package com.example.galloween2.controllers;

import com.example.galloween2.controllers.dtos.request.CreateDestinationTypeOfTripAvailableRequest;
import com.example.galloween2.controllers.dtos.responses.CreateDestinationTypeOfTripAvailableResponse;
import com.example.galloween2.controllers.dtos.responses.DestinationTypeOfTripAvailableResponse;
import com.example.galloween2.services.interfaces.IDestinationTypeOfTripAvailableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("destination-type-of-trip-available")
public class DestinationTypeOfTripAvailableController {
    @Autowired
    private IDestinationTypeOfTripAvailableService service;


    @PostMapping
    public CreateDestinationTypeOfTripAvailableResponse create(@RequestBody CreateDestinationTypeOfTripAvailableRequest request) {
        return service.create(request);
    }

    @GetMapping("{typeOfTrip}")
    public List<DestinationTypeOfTripAvailableResponse> get(@PathVariable String typeOfTrip) {
        return service.get(typeOfTrip);
    }

    @GetMapping
    public List<CreateDestinationTypeOfTripAvailableResponse> list() {
        return service.list();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

