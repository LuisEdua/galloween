package com.example.galloween2.services.interfaces;

import com.example.galloween2.controllers.dtos.request.CreateUserRequest;
import com.example.galloween2.controllers.dtos.request.ValidateUserRequest;
import com.example.galloween2.controllers.dtos.responses.CreateUserResponse;
import com.example.galloween2.controllers.dtos.responses.ValidateUserResponse;
import com.example.galloween2.entities.User;

import java.util.List;

public interface IUserService {
    CreateUserResponse create(CreateUserRequest request);

    CreateUserResponse get(Long id);

    List<CreateUserResponse> list();

    CreateUserResponse update(Long id, CreateUserRequest request);

    void delete(Long id);

    User findById(Long id);

    ValidateUserResponse validate(ValidateUserRequest request);
}
