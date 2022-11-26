package com.example.galloween2.controllers;

import com.example.galloween2.controllers.dtos.request.CreateUserRequest;
import com.example.galloween2.controllers.dtos.request.ValidateUserRequest;
import com.example.galloween2.controllers.dtos.responses.BaseResponse;
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

    @PostMapping("{role}")
    public BaseResponse create(@RequestBody CreateUserRequest request, @PathVariable Long role) {
        return service.create(request, role);
    }

    @PostMapping("validate")
    public BaseResponse validate(@RequestBody ValidateUserRequest request){ return service.validate(request);}

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

