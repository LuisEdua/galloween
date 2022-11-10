package com.example.galloween2.services.interfaces;

import com.example.galloween2.controllers.dtos.request.CreateCommentRequest;
import com.example.galloween2.controllers.dtos.responses.CommentResponse;

import java.util.List;

public interface ICommentService {
    CommentResponse create(CreateCommentRequest request, Long user_id);

    List<CommentResponse> list();

    CommentResponse update(Long id, CreateCommentRequest request);

    void delete(Long id);

}
