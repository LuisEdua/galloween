package com.example.galloween2.services;

import com.example.galloween2.controllers.dtos.request.CreateUserRequest;
import com.example.galloween2.controllers.dtos.request.ValidateUserRequest;
import com.example.galloween2.controllers.dtos.responses.BaseResponse;
import com.example.galloween2.controllers.dtos.responses.CreateReservationResponse;
import com.example.galloween2.controllers.dtos.responses.CreateUserResponse;
import com.example.galloween2.controllers.dtos.responses.ValidateUserResponse;
import com.example.galloween2.controllers.exceptions.UserValidateException;
import com.example.galloween2.entities.User;
import com.example.galloween2.entities.projections.UserProjection;
import com.example.galloween2.repositories.IUserRepository;
import com.example.galloween2.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository repository;

    @Lazy
    @Autowired
    private IRoleService roleService;

    @Lazy
    @Autowired
    private ICommentService commentService;

    @Lazy
    @Autowired
    private IReservationService reservationService;

    @Lazy
    @Autowired
    private IPaymentService paymentService;

    @Override
    public BaseResponse create(CreateUserRequest request, Long role) {
        User user = from(request, role);


            return BaseResponse.builder()
                    .data(from(repository.save(user)))
                    .message("User created correctly")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.CREATED).build();

    }

    @Override
    public void delete(Long id) {
        reservationService.userNullAll(id);
        paymentService.findByUser(id);
        repository.delete(findAndEnsureExist(id));
    }

    @Override
    public User findById(Long id) {
        return findAndEnsureExist(id);
    }

    @Override
    public BaseResponse validate(ValidateUserRequest request) {
        UserProjection user =from(request);
        try {
        return BaseResponse.builder()
                .data(from(user))
                .message("User validated correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED).build();
        } catch (Exception e) {
            throw new UserValidateException("User not found");
        }
    }

    private BaseResponse from(CreateReservationResponse response){
        return BaseResponse.builder().data(response)
                .message("BaseResponse created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED).build();
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
        response.setRole(user.getRole().getRole());
        return response;
    }

    private User findAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
}
