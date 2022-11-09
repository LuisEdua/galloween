package com.example.galloween2.services.interfaces;

import com.example.galloween2.controllers.dtos.request.CreateUserRequest;
import com.example.galloween2.controllers.dtos.request.ValidateUserRequest;
import com.example.galloween2.controllers.dtos.responses.BaseResponse;
import com.example.galloween2.controllers.dtos.responses.CreateUserResponse;
import com.example.galloween2.entities.User;

import java.util.List;

public interface IUserService {
    BaseResponse create(CreateUserRequest request, Long role);


    void delete(Long id);

    User findById(Long id);

    BaseResponse validate(ValidateUserRequest request);
}
