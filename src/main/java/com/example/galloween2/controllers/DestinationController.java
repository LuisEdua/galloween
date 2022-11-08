package com.example.galloween2.controllers;

import com.example.galloween2.controllers.dtos.request.CreateDestinationRequest;
import com.example.galloween2.controllers.dtos.responses.CreateDestinationResponse;
import com.example.galloween2.services.interfaces.IDestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("destination")
public class DestinationController {
    @Autowired
    private IDestinationService service;


    @PostMapping
    public CreateDestinationResponse create(@RequestParam String city, @RequestParam String state,
                                            @RequestParam String country, @RequestParam MultipartFile file) {
        return service.create(city, state, country, file);
    }

    @GetMapping("{id}")
    public CreateDestinationResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping
    public List<CreateDestinationResponse> list() {
        return service.list();
    }

    @PutMapping("{id}")
    public CreateDestinationResponse update(@PathVariable Long id, @RequestBody CreateDestinationRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

