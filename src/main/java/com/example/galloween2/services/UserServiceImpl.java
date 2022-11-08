package com.example.galloween2.services;

import com.example.galloween2.controllers.dtos.request.CreateUserRequest;
import com.example.galloween2.controllers.dtos.request.ValidateUserRequest;
import com.example.galloween2.controllers.dtos.responses.CreateUserResponse;
import com.example.galloween2.controllers.dtos.responses.ValidateUserResponse;
import com.example.galloween2.entities.User;
import com.example.galloween2.entities.projections.UserProjection;
import com.example.galloween2.repositories.IUserRepository;
import com.example.galloween2.services.interfaces.IRoleService;
import com.example.galloween2.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository repository;
    @Autowired
    private IRoleService roleService;

    @Override
    public CreateUserResponse create(CreateUserRequest request, Long role) {
        User save = repository.save(from(request, role));

        return from(save);
    }

    @Override
    public CreateUserResponse get(Long id) {
        User user = findAndEnsureExist(id);
        return from(user);
    }

    @Override
    public List<CreateUserResponse> list() {
        return repository.findAll().stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public CreateUserResponse update(Long id, CreateUserRequest request) {
        User user = findAndEnsureExist(id);
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setAge(request.getAge());
        user.setCellphone(request.getCellphone());
        return from(repository.save(user));
    }

    @Override
    public void delete(Long id) {
        repository.delete(findAndEnsureExist(id));
    }

    @Override
    public User findById(Long id) {
        return findAndEnsureExist(id);
    }

    @Override
    public ValidateUserResponse validate(ValidateUserRequest request) {
        return from(from(request));
    }

    private ValidateUserResponse from(UserProjection projection) {
        ValidateUserResponse response = new ValidateUserResponse();
        response.setId(projection.getUserId());
        response.setFullName(projection.getUserFullName());
        response.setAge(projection.getUserAge());
        response.setCellphone(projection.getUserCellphone());
        response.setRole(projection.getUserRole());
        return response;
    }

    private UserProjection from(ValidateUserRequest request){
        return repository.findUserByEmailAndPassword(request.getEmail(), request.getPassword());
    }

    private User from(CreateUserRequest request, Long role) {
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setAge(request.getAge());
        user.setCellphone(request.getCellphone());
        user.setRole(roleService.findById(role));
        return user;
    }

    private CreateUserResponse from(User user){
        CreateUserResponse response = new CreateUserResponse();
        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setPassword(user.getPassword());
        response.setAge(user.getAge());
        response.setCellphone(user.getCellphone());
        return response;
    }

    private User findAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
}
