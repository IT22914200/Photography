package com.example.photographsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.photographsystem.models.Comment;
import com.example.photographsystem.models.PhotoPost;
import com.example.photographsystem.models.Notification;
import com.example.photographsystem.models.User;
import com.example.photographsystem.repositories.CommentRepository;
import com.example.photographsystem.repositories.PhotoPostRepository;
import com.example.photographsystem.repositories.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PhotoPostRepository cookingPostRepository;

    private final NotificationService notificationService;

    @Autowired
    public CommentService(CommentRepository commentRepository,
                          UserRepository userRepository,
                          PhotoPostRepository cookingPostRepository,NotificationService notifcationService) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.cookingPostRepository = cookingPostRepository;
        this.notificationService = notifcationService;
    }

    // Get all comments
    public List<Comment> getAllComments() {
        return commentRepository.findAllByDeleteStatusFalse();
    }

    

    

    

    

   


}