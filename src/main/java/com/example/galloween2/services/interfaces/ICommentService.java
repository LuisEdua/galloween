package com.example.galloween2.services.interfaces;

import com.example.galloween2.controllers.dtos.request.CreateCommentRequest;
import com.example.galloween2.controllers.dtos.responses.CreateCommentResponse;

import java.util.List;

public interface ICommentService {
    CreateCommentResponse create(CreateCommentRequest request, Long user_id);

    CreateCommentResponse get(Long id);

    List<CreateCommentResponse> list();

    CreateCommentResponse update(Long id, CreateCommentRequest request);

    void delete(Long id);
}
