package com.example.galloween2.services.interfaces;

import com.example.galloween2.controllers.dtos.request.CreateRoleRequest;
import com.example.galloween2.controllers.dtos.responses.CreateRoleResponse;
import com.example.galloween2.entities.Role;
import com.example.galloween2.entities.User;

import java.util.List;

public interface IRoleService {
    CreateRoleResponse create(CreateRoleRequest request);

    CreateRoleResponse get(Long id);

    List<CreateRoleResponse> list();

    CreateRoleResponse update(Long id, CreateRoleRequest request);

    void delete(Long id);

    Role findById(Long id);
}
