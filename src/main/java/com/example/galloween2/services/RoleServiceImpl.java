package com.example.galloween2.services;

import com.example.galloween2.controllers.dtos.request.CreateRoleRequest;
import com.example.galloween2.controllers.dtos.responses.CreateRoleResponse;
import com.example.galloween2.entities.Role;
import com.example.galloween2.repositories.IRoleRepository;
import com.example.galloween2.services.interfaces.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleRepository repository;

    @Override
    public CreateRoleResponse create(CreateRoleRequest request) {
        Role save = repository.save(from(request));

        return from(save);
    }


    @Override
    public Role findById(Long id) {
        return findAndEnsureExist(id);
    }

    private Role from(CreateRoleRequest request){
        Role role = new Role();
        role.setRole(request.getRole());
        return role;
    }

    private CreateRoleResponse from(Role role){
        CreateRoleResponse response = new CreateRoleResponse();
        response.setId(role.getId());
        response.setRole(role.getRole());
        return response;
    }

    private Role findAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
}
