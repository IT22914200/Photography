package com.example.photographsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.photographsystem.dtos.CommentDto;
import com.example.photographsystem.models.Comment;
import com.example.photographsystem.models.PhotoPost;
import com.example.photographsystem.models.User;
import com.example.photographsystem.services.CommentService;
import com.example.photographsystem.services.PhotoPostService;
import com.example.photographsystem.services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;
    private final PhotoPostService postService;
    private final UserService userService;

    @Autowired
    public CommentController(CommentService commentService,PhotoPostService postService,UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
        this.postService = postService;
    }

    // Get all comments
    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentService.getAllComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    

    

    

   

   


}