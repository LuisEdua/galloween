package com.example.galloween2.services;

import com.example.galloween2.controllers.dtos.request.CreateCommentRequest;
import com.example.galloween2.controllers.dtos.responses.CreateCommentResponse;
import com.example.galloween2.entities.Comment;
import com.example.galloween2.entities.User;
import com.example.galloween2.repositories.ICommentRepository;
import com.example.galloween2.services.interfaces.ICommentService;
import com.example.galloween2.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private ICommentRepository repository;

    /*@Autowired
    private IClientService clientService;*/

    @Autowired
    private IUserService userService;

    @Override
    public CreateCommentResponse create(CreateCommentRequest request, Long id) {
        Comment save = repository.save(from(request));

        return from(save);
    }

    @Override
    public CreateCommentResponse get(Long id) {
        Comment comment = findAndEnsureExist(id);
        return from(comment);
    }

    @Override
    public List<CreateCommentResponse> list() {
        return repository.findAll().stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public CreateCommentResponse update(Long id, CreateCommentRequest request) {
        Comment comment = findAndEnsureExist(id);
        comment.setComment(request.getComment());
        comment.setDate(request.getDate());
        return from(repository.save(comment));
    }

    @Override
    public void delete(Long id) {
        repository.delete(findAndEnsureExist(id));
    }

    private Comment from(CreateCommentRequest request){
        Comment comment = new Comment();
        comment.setComment(request.getComment());
        comment.setDate(request.getDate());
        /*Client client = clientService.findById(request.getUserId());
        comment.setClient(client);*/
        User user = userService.findById(request.getUserId());
        comment.setUser(user);



        return comment;
    }

    private CreateCommentResponse from(Comment comment){
        CreateCommentResponse response = new CreateCommentResponse();
        response.setId(comment.getId());
        response.setComment(comment.getComment());
        response.setDate(comment.getDate());
        /*Client client = comment.getClient();
        response.setUserId(client.getId());*/
        User user = comment.getUser();
        response.setUserId(user.getId());
        return response;
    }

    private Comment findAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
}
