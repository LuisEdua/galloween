package com.example.galloween2.controllers;

import com.example.galloween2.controllers.dtos.request.CreateUserRequest;
import com.example.galloween2.controllers.dtos.request.ValidateUserRequest;
import com.example.galloween2.controllers.dtos.responses.CreateUserResponse;
import com.example.galloween2.controllers.dtos.responses.ValidateUserResponse;
import com.example.galloween2.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private IUserService service;

    @PostMapping
    public CreateUserResponse create(@RequestBody CreateUserRequest request) {
        return service.create(request);
    }

    @GetMapping("validate")
    public ValidateUserResponse validate(@RequestBody ValidateUserRequest request){ return service.validate(request);}

    @GetMapping("{id}")
    public CreateUserResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping
    public List<CreateUserResponse> list() {
        return service.list();
    }

    @PutMapping("{id}")
    public CreateUserResponse update(@PathVariable Long id, @RequestBody CreateUserRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

