package com.example.galloween2.controllers;

import com.example.galloween2.controllers.dtos.request.CreateTypeOfTripAvailableRequest;
import com.example.galloween2.controllers.dtos.responses.CreateTypeOfTripAvailableResponse;
import com.example.galloween2.services.interfaces.ITypeOfTripAvailableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("type-of-trip-available")
public class TypeOfTripAvailableController {
    @Autowired
    private ITypeOfTripAvailableService service;


    @PostMapping
    public CreateTypeOfTripAvailableResponse create(@RequestBody CreateTypeOfTripAvailableRequest request) {
        return service.create(request);
    }

    @GetMapping("{id}")
    public CreateTypeOfTripAvailableResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping
    public List<CreateTypeOfTripAvailableResponse> list() {
        return service.list();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

