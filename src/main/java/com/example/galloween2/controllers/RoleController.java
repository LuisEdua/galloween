package com.example.galloween2.controllers;

import com.example.galloween2.controllers.dtos.request.CreateRoleRequest;
import com.example.galloween2.controllers.dtos.responses.CreateRoleResponse;
import com.example.galloween2.services.interfaces.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    private IRoleService service;


    @PostMapping
    public CreateRoleResponse create(@RequestBody CreateRoleRequest request) {
        return service.create(request);
    }

}

