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

    @GetMapping("{id}")
    public CreateRoleResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping
    public List<CreateRoleResponse> list() {
        return service.list();
    }

    @PutMapping("{id}")
    public CreateRoleResponse update(@PathVariable Long id, @RequestBody CreateRoleRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

