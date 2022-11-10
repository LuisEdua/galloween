package com.example.galloween2.services;

import com.example.galloween2.controllers.dtos.request.CreateCommentRequest;
import com.example.galloween2.controllers.dtos.responses.CommentResponse;
import com.example.galloween2.entities.Comment;
import com.example.galloween2.repositories.ICommentRepository;
import com.example.galloween2.services.interfaces.ICommentService;
import com.example.galloween2.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private ICommentRepository repository;

    @Lazy
    @Autowired
    private IUserService userService;

    @Override
    public CommentResponse create(CreateCommentRequest request, Long id) {
        Comment save = repository.save(from(request, id));

        return from(save);
    }
    @Override
    public List<CommentResponse> list() {
        return repository.findAll().stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public CommentResponse update(Long id, CreateCommentRequest request) {
        Comment comment = findAndEnsureExist(id);
        comment.setComment(request.getComment());
        comment.setDate(request.getDate());
        return from(repository.save(comment));
    }

    @Override
    public void delete(Long id) {
        repository.delete(findAndEnsureExist(id));
    }

    private Comment from(CreateCommentRequest request, Long id){
        Comment comment = new Comment();
        comment.setComment(request.getComment());
        comment.setDate(request.getDate());
        comment.setUser(userService.findById(id));
        return comment;
    }

    private CommentResponse from(Comment comment){
        CommentResponse response = new CommentResponse();
        response.setId(comment.getId());
        response.setComment(comment.getComment());
        response.setDate(comment.getDate());
        response.setUser(comment.getUser().getFullName());
        return response;
    }

    private Comment findAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
}
