package com.example.galloween2.services;

import com.example.galloween2.controllers.dtos.request.CreateRoleRequest;
import com.example.galloween2.controllers.dtos.responses.CreateRoleResponse;
import com.example.galloween2.entities.Role;
import com.example.galloween2.entities.User;
import com.example.galloween2.repositories.IRoleRepository;
import com.example.galloween2.services.interfaces.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public CreateRoleResponse get(Long id) {
        Role role = findAndEnsureExist(id);
        return from(role);
    }

    @Override
    public List<CreateRoleResponse> list() {
        return repository.findAll().stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public CreateRoleResponse update(Long id, CreateRoleRequest request) {
        Role role = findAndEnsureExist(id);
        role.setRole(request.getRole());
        return from(repository.save(role));
    }

    @Override
    public void delete(Long id) {
        repository.delete(findAndEnsureExist(id));
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
